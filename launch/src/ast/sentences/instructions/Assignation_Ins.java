package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;
import ast.Utils;

public class Assignation_Ins extends Instruction {
    private Expression leftSide;

    public Assignation_Ins(Expression left, Expression right, int row) {
        super(right, null, row);
        this.leftSide = left;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(leftSide.toString() + " = " + this.argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }

    @Override
    public void bind() {
        leftSide.bind();
        argExpression.bind();
    }
}