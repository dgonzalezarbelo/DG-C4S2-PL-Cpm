package ast.expressions.operators;

import ast.Josito;
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

    @Override
    public void generateAddress(Josito jose) { // Code_D
        // opnd1.generateAddress(jose);
        // type.getSize()
        // opnd2.generateValue(jose);
        // jose.mult
        // jose.suma
    }
    
    @Override
    public void generateValue(Josito jose) { // Code_E
        // opnd1.generateAddress(jose);
        // opnd2.generateValue(jose);
        // jose.suma
        // jose.load
    }
}

/*
 * int a[6][10]
 * a[5]         <- Este
 * a[5][1][3]      <- Y este te dan la misma address al hacer Code_D
 *
 * 
 * int c[100];
 * ~b = c[5];
 * 
 * 
 * int d;
 * int j[10]; // j[0] = j mismo CODE_D
 * // *j = j[0];
 * j = a[5];
 * a[5] = j;
 * j[0] = a[5]
 * d = a[5][0]
 * 
 * int~ b;
 * b = a[5][2];
 * 
 * int e[5][10];
 * e[2] = a[2];
 */
