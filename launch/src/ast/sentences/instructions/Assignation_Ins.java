package ast.sentences.instructions;

import java.util.List;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Const_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.DimenssionException;
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
        super.bind();
    }

    @Override
    public void checkType() throws Exception {
        try {
            super.checkType();
            leftSide.checkType();
            Type left = leftSide.getType();
            Type right = argExpression.getType();
            if (!left.canBeAssigned(right))
                throw new MatchingTypeException(String.format("Left and right sides types ('%s' and '%s') of assignation doesn't match", left, right));
            if (left.getKind() == Type_T.ARRAY) {
                List<Expression> l1 = ((Array_Type)left).getDimenssions(), l2 = ((Array_Type)right).getDimenssions();
                if (l1.size() != l2.size())
                    throw new DimenssionException("Number of dimenssions at both sides of assignation do not match");
                for (int i = 0; i < l1.size(); i++) {
                    if (!((Const_Type) l1.get(i).getType()).getConstValue().equals(((Const_Type) l2.get(i).getType()).getConstValue()))
                        throw new DimenssionException("Array sizes at both sides of assignation do not match");         
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }
}