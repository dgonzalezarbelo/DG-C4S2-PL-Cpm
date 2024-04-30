package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;
import ast.types.Pointer_Type;
import ast.types.Type;
import exceptions.InvalidTypeException;


public class Reference_Op extends EUnary {
    public Reference_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "&" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type t = this.opnd1().checkType();
        if (t == null)
            throw new InvalidTypeException("Referenced expression must have type");
        return new Pointer_Type(t, row);
    }
}