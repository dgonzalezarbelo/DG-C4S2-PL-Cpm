package ast.expressions.operators;

import ast.preamble.Constructor;
import ast.types.Type;
import exceptions.InvalidTypeException;

public class ConstructorCall extends FunctionCall { 
    
    public ConstructorCall(FunctionCall f, int row) {
        super(f.id, f.args, row);
    }

    @Override
    public Type checkType() throws Exception {
        Type t = super.checkType();
        if (!matchedFunction.getClass().equals(Constructor.class))
            throw new InvalidTypeException("Constructor call expected but function call was written in row " + row);
        return t;
    }
}