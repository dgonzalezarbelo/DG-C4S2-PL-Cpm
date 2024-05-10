package ast.sentences.instructions;

import java.util.List;

import ast.Josito;
import ast.expressions.Expression;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Const_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.DimenssionException;
import exceptions.MatchingTypeException;
import exceptions.UnexpectedTypeException;
import utils.Utils;

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
                Array_Type cast = (Array_Type)left;
                if (cast.isSubarray())
                    throw new UnexpectedTypeException("A non-singular subarray can not be to the left of an assignation");
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

    @Override
    public void generateCode(Josito jose) { 
        Type t = leftSide.getType();
        switch (t.getKind()) {
            case INT:
            case BOOL:
			case POINTER:
            try {
                    leftSide.generateAddress(jose);
                    argExpression.generateValue(jose);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Utils.printErrorRow(row);
                }  // Right side
				jose.store();
				break;
			case ARRAY:
			case CLASS:
            case STRUCT:
                try {
                    argExpression.generateValue(jose);
                    leftSide.generateAddress(jose);     // Dest address
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Utils.printErrorRow(row);
                }  // Src address
                jose.createConst(t.getSize());      // N size to copy
                jose.copy_n();
                break;
            case CONST:
                // A const type can not be to the left of an assignation
            default:
                break;
        }
    }
}