package ast.expressions.operands;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.Expression;
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
    protected ASTNode matchingBind;                 // FIXME igual esto debería ser una definicion de funcion y no un astnode

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

    // TODO del Code_E
}