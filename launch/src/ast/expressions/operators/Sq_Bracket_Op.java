package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;
import ast.types.Envelope_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;


public class Sq_Bracket_Op extends EBin {
    public Sq_Bracket_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "[" + opnd2().toString() + "]";}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type leftSide = this.opnd1().checkType();
        Type rightSide = this.opnd2().checkType();
        if (leftSide.getKind() != Type_T.ARRAY)
            throw new InvalidTypeException("Array access operator '[]' is only applicable for array variables at row " + this.row);
        if (rightSide.getKind() != Type_T.INT)
            throw new InvalidTypeException("Array access index '[index]' must be an integer at row " + this.row);
        return ((Envelope_Type) leftSide).getInnerType();
    }
}
