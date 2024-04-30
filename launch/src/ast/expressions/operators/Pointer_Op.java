package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;
import ast.types.Envelope_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;


public class Pointer_Op extends EUnary {
    public Pointer_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "~" + opnd1().toString();}

    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type t = this.opnd1().checkType();
        if (t.getKind() != Type_T.POINTER)
            throw new InvalidTypeException("Pointer operator '~' is only applicable for expressions of pointer type");
        return ((Envelope_Type) t).getInnerType();
    }
}
