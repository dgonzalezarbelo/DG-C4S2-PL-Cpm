package ast.expressions.operators;

import ast.Josito;
import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidTypeException;

public class Sq_Bracket_Op extends BinaryExpression {
    private static int dynamic_access_pos;

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

    @Override
    public void generateAddress(Josito jose) throws Exception { // Code_D
        Expression op1 = opnd1();
        Array_Type cast = (Array_Type)op1.getType();
        if (!cast.isDynamic()) {
            if (op1 instanceof Sq_Bracket_Op)   // If we have more brackets to the left then everything work as per usual
                op1.generateAddress(jose);
            else
                op1.generateValue(jose);        // In other case, we want the first position pointed by the (outer) array, so we want the value, not the address of the array itself
            jose.createConst(type.getSize());
            opnd2().generateValue(jose);
            jose.translateOperator(this.operator);
        }
        else {
            if (op1 instanceof Sq_Bracket_Op) {
                op1.generateAddress(jose);
                dynamic_access_pos++;
            }
            else {
                op1.generateValue(jose);
                dynamic_access_pos = 1;
            }
            jose.dynamicArrayAccess(opnd2(), dynamic_access_pos);
        }
    }

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E
        generateAddress(jose);
        if (type.getKind() != Type_T.ARRAY) { // If the type is an array itself we just want the address
            Type_T t = this.type.getKind();
            switch (t) {
                case INT:
                case BOOL:
                case POINTER:
                    jose.load();
                    break;
                case ARRAY:
                    // This will never be the case
                case CLASS:
                case STRUCT:
                    // In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
                    break;
                case CONST: // This will only be a define
                    // This will never be the case
                    break;
                default:
                    break;    
            }
        }
    }
}
