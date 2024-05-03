package ast.expressions.operators;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.expressions.Expression;
import ast.preamble.Function;
import ast.types.Type;
import exceptions.UndefinedFunctionException;


public class MethodCall extends FunctionCall { 

    public MethodCall(String opnd1, List<Expression> opnd2, int row) {
        super(opnd1, opnd2, row);
    }

    public MethodCall(FunctionCall fc, int row) {
        super(fc.id, fc.args, row);
    }

    @Override
    public void bind() { 
        for (Expression exp : args)
          exp.bind();
    }

    @Override
    public Type checkType() throws Exception {
        for (Expression arg : args)
            typeArgs.add(arg.checkType());
        return null;
    }
}