package ast.expressions.operands;

import ast.preamble.Constructor;
import exceptions.InvalidTypeException;

public class ConstructorCall extends FunctionCall { 
    
    public ConstructorCall(FunctionCall f, int row) {
        super(f.funcname, f.args, row);
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        if (!matchingBind.getClass().equals(Constructor.class))
            throw new InvalidTypeException("Constructor call expected but function call was written");
    }

    // TODO del Code_E o el Code_D
}