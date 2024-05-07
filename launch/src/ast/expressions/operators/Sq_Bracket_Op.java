package ast.expressions.operators;

import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidTypeException;


public class Sq_Bracket_Op extends BinaryExpression {
    public Sq_Bracket_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
        this.operator = Operator_T.SQ_BRACKET;
    }
    
    public String toString() {return opnd1().toString() + "[" + opnd2().toString() + "]";}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type leftSide = opnd1().getType();
        Type rightSide = opnd2().getType();
        if (leftSide.getKind() != Type_T.ARRAY)
            throw new InvalidTypeException("Array access operator '[]' is only applicable for array variables at row " + this.row);
        if (rightSide.getKind() != Type_T.INT)
            throw new InvalidTypeException("Array access index '[index]' must be an integer at row " + this.row);
        this.type = ((Array_Type) leftSide).getInnerType(); // aquí no pasa nada por no bindear Array_Type porque su interno ya estaba bindeado
        this.type.checkType();
    }
}
