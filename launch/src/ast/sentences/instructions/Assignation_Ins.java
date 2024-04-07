package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;

public class Assignation_Ins extends Instruction {
    private Expression leftSide;

    public Assignation_Ins(Expression left, Expression right) {
        super(right, null);
        this.leftSide = left;
    }

    public String toString() {return leftSide.toString() + "=" + this.argExpression.toString();}

    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
}