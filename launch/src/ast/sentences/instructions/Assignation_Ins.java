package ast.sentences.instructions;

import java.util.List;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.Array_Type;
import ast.types.Const_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
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
        argExpression.bind();
    }

    @Override
    public Type checkType() throws Exception {
        try {
            Type left = leftSide.checkType();
            Type right = argExpression.checkType();
            if (!left.canBeAssigned(right))
                throw new MatchingTypeException(String.format("Left and right sides types ('%s' and '%s') at row %d assignation doesn't match", left, right, this.row));
            if (left.getKind() == Type_T.ARRAY) {
                List<Expression> l1 = ((Array_Type)left).getDimenssions(), l2 = ((Array_Type)right).getDimenssions();
                if (l1.size() != l2.size())
                    throw new DimenssionException("Number of dimenssions at both sides of assignation do not match at row " + row); //TODO
                for (int i = 0; i < l1.size(); i++) {
                    if (!((Const_Type) l1.get(i).getType()).getConstValue().equals(((Const_Type) l2.get(i).getType()).getConstValue()))
                        throw new DimenssionException("Array sizes at both sides of assignation do not match at row " + row);         
                }
            }
            return left;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}