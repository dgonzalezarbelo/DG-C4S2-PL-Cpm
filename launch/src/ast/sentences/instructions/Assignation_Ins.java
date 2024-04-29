package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.Type;
import exceptions.MatchingTypeException;

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
    public void bind() {
        leftSide.bind();
        argExpression.bind();
    }

    @Override
    public Type checkType() throws Exception {
        try {
            Type left = leftSide.checkType();
            Type right = argExpression.checkType();
            if (!left.equals(right))
                throw new MatchingTypeException("Left and right sides types in " + row + "assignation doesn't match");
            return left;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}