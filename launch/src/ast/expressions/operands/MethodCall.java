package ast.expressions.operands;

import java.util.List;

import ast.expressions.Expression;
import ast.preamble.Method;
import exceptions.UndefinedFunctionException;
import exceptions.VisibilityException;

public class MethodCall extends Field {
    private FunctionCall func;

    public MethodCall(String opnd1, List<Expression> opnd2, int row) {
        super(opnd1, row);
        func = new FunctionCall(this.fieldname, opnd2, row);
    }

    public MethodCall(FunctionCall fc, int row) {
        super(fc.funcname, row);
        this.func = fc;
    }

    @Override
    public void bind() { 
        func.bind();
    }

    @Override
    public String toString() {return this.func.toString(); }

    @Override
    public void checkType() throws Exception {
        try {
            func.checkType();
        } catch (UndefinedFunctionException e) {
            // Nothing to do, there was no candidates as expected
        } catch (Exception e) {
            throw e;
        }
        
        try {
            Method matched = classFrom.hasMethod(func); 
            this.type = matched.getType();
        } catch (Exception e) {
            throw new VisibilityException(e.getMessage());
        }
    }

    // TODO del Code_E
}