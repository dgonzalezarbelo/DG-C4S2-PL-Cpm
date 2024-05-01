package ast.expressions.operators;

import java.util.List;

import ast.expressions.Expression;


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
}