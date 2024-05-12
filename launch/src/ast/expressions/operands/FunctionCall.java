package ast.expressions.operands;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Josito;
import ast.expressions.Expression;
import ast.preamble.Argument;
import ast.preamble.Constructor;
import ast.preamble.Function;
import ast.preamble.Program;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Type;
import exceptions.InvalidIdException;
import exceptions.UndefinedFunctionException;
import utils.Utils;

public class FunctionCall extends Expression { 
    protected String funcname;
    protected List<ASTNode> possibleBinds;          // Constructor and Parenthesis list
    protected List<Expression> args;
    protected List<Type> typeArgs;
    protected Function matchingBind;
    protected int reference;

    public FunctionCall(String funcname, List<Expression> funcargs, int row) {
        this.funcname = funcname;
        this.args = funcargs;
        this.row = row;
        this.typeArgs = new ArrayList<>();
        this.reference = 0;
    }
    
    public String toString() {return funcname + args.toString();}

    @Override
    public void bind() {
        try {
            this.possibleBinds = Program.symbolsTable.getFuncAndConstructsDefinitions(this.funcname);
        } catch (InvalidIdException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        for (Expression exp : args)
            exp.bind();
    }

    @Override
    public void checkType() throws Exception {
        for (Expression arg : args) {
            arg.checkType();
            typeArgs.add(arg.getType());
        }
        if (possibleBinds == null)
            throw new UndefinedFunctionException("Function name " + this.funcname + " is not defined");
        List<Function> candidates = new ArrayList<>();
        for (ASTNode node : possibleBinds)
            candidates.add((Function) node);
        Function matched = matchWith(candidates);
        matchingBind = matched;
        this.type = matched.getType();
    }

    public Function matchWith(List<Function> fs) throws Exception {
        for (Function f : fs) {
            if (!f.getDefinitionName().equals(funcname))
                continue;
            boolean match = true;
            List<Type> types = f.getArgumentTypes();
            if (types.size() != args.size())
                continue;
            for (int i = 0; i < args.size(); i++) {
                if (!types.get(i).canBeAssigned(typeArgs.get(i))) {
                    match = false;
                    break;
                }
            }
            if (match)
                return f;
        }
        throw new UndefinedFunctionException("There is no function that matches " + this.funcname + " at row " + this.row);
    }

    @Override
    public Expression opnd1() {
        throw new UnsupportedOperationException("Function calls does not have operands");
    }

    @Override
    public Expression opnd2() {
        throw new UnsupportedOperationException("Function calls does not have operands");
    }

    public void setReference(int value) {
        this.reference = value;
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        this.generateArgumentsPlacement(jose);
        jose.createConst(matchingBind.getSize());           // calc new scope size
        jose.reserveStackCall();                            // calc new limits MP and SP of scope
        jose.setDynamicLink();                              // saves previous MP to return in DL of new scope
        if (matchingBind instanceof Constructor)
            jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());               // because its a constructor saves the returning reference object
        else
            jose.setReference(reference);                       // saves the reference object since we need to calc the direction (if needed), in this case 0 because we are not inside one
        jose.callFunction(matchingBind.getWASMId());        //calling the WASM function with the unique id
        // return value (if any) is now at top of the stack, so the generateValue() is complete for functionCall
        jose.consumeTrash();                                // consuming the returning value
        jose.getReturnAddress(matchingBind.getSize());
        jose.freeStackCall();
    }

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E
        this.generateArgumentsPlacement(jose);
        jose.createConst(matchingBind.getSize());           // calc new scope size
        jose.reserveStackCall();                            // calc new limits MP and SP of scope
        jose.setDynamicLink();                              // saves previous MP to return in DL of new scope
        if (matchingBind instanceof Constructor)
            jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());               // because its a constructor saves the returning reference object
        else
            jose.setReference(reference);                       // saves the reference object since we need to calc the direction (if needed), in this case 0 because we are not inside one
        jose.callFunction(matchingBind.getWASMId());        //calling the WASM function with the unique id
        // return value (if any) is now at top of the stack, so the generateValue() is complete for functionCall
        jose.freeStackCall();
    }

    protected void generateArgumentsPlacement(Josito jose) throws Exception {
        int i = 0;
        for(Argument declared_arg : matchingBind.getArgumentsList()) {
            Type left_t = declared_arg.getType();
            switch (left_t.getKind()) {
                case INT:
                case BOOL:
                case POINTER:
                    if (!declared_arg.isReference()) {
                        jose.getLocalDirUsingSP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.store();
                    }
                    else {
                        jose.getLocalDirUsingSP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case ARRAY:
                    Array_Type cast = (Array_Type)left_t;
                    if (!cast.isDynamic()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingSP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.generateDynamicArrayInformation(declared_arg, args.get(i), cast.getInnerTerminalType().getSize());
                    }
                    break;
                case CLASS:
                case STRUCT:
                    if (!declared_arg.isReference()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingSP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.getLocalDirUsingSP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case CONST:
                    // A declared type left (in the function definition) for an argument can not be a constant
                default:
                    break;
            }
            i++;
        }
    }
}