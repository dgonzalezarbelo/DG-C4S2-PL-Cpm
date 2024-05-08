package ast.expressions.operands;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Josito;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Argument;
import ast.preamble.Function;
import ast.preamble.Program;
import ast.types.interfaces.Type;
import exceptions.InvalidIdException;
import exceptions.UndefinedFunctionException;

public class FunctionCall extends Expression { 
    protected String funcname;
    protected List<ASTNode> possibleBinds;          // Constructor and Parenthesis list
    protected List<Expression> args;
    protected List<Type> typeArgs;
    protected Function matchingBind;

    public FunctionCall(String funcname, List<Expression> funcargs, int row) {
        this.funcname = funcname;
        this.args = funcargs;
        this.row = row;
        this.typeArgs = new ArrayList<>();
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
            boolean match = true;
            List<Type> types = f.getArgumentTypes();
            if (typeArgs.size() != args.size())
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

    
    // FIXME igual hay que hacer aquí algo
    // public void computeOffset();
    
    // FIXME igual hay que hacer aquí algo
    // public void maxMemory(Integer c, Integer maxi)

    public void generateValue(Josito jose) { // Code_E 
        jose.createConst(matchingBind.getSize());   // calc new scope size
        jose.reserveStackCall();                    // calc new limits MP and SP of scope
        jose.setDynamicLink();                      // saves previous MP to return in DL of new scope
        jose.setReference(0);                       // saves the reference object since we need to calc the direction (if needed), in this case 0 because we are not inside one
        int i = 0;                     
        for(Argument arg : matchingBind.getArgumentsList()) {   // TODO es prácticamente igual que el de la asignación así que hay que ver como se cambia
            jose.getLocalDirUsingMP(arg.getOffset());   // Code_D of the argument
            args.get(i).generateValue(jose);            // Code_E of the parameter
            i++;
        } // TODO en el for hacer distinto si es parametro por referencia
        jose.callFunction(matchingBind.getWASMId());    //calling the WASM function with the unique id
        // return value (if any) is now at top of the stack, so the generateValue() is complete for functionCall
        jose.freeStackCall(); // FIXME esto podría petar porque el return value está en la pila y estamos llamando a otra función, pero pensamos que no
    }
}