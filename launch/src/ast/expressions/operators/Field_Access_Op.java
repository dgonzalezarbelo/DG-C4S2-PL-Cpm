package ast.expressions.operators;

import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.expressions.operands.Field;
import ast.expressions.operands.ThisID;
import ast.types.interfaces.Defined_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidTypeException;

public class Field_Access_Op extends BinaryExpression {
    public Field_Access_Op(Expression opnd1, Field opnd2, int row) { // TODO hay que cambiar esto en el cup
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "." + opnd2().toString(); };

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public void checkType() throws Exception {
        opnd1().checkType();
        Type leftSide = opnd1().getType();
        if (leftSide.getKind() != Type_T.CLASS && leftSide.getKind() != Type_T.STRUCT)
            throw new InvalidTypeException("Field access operator '.' only applicable to classes or structs");
        boolean isThis = opnd1().getClass().equals(ThisID.class);
        ((Field) opnd2()).setClassFrom(((Defined_Type) opnd1().getType()), isThis);
        opnd2().checkType();
        this.type = opnd2().getType();
    }
}


/*
 * struct CS1 {
 *      int c;
 * }
 * 
 * struct CS2{
 *      CS1 b;
 * }
 * 
 * 
 * int main() {
 *      CS2 a;
 *      ...
 * 
 *      a.b.c
 * 
 *      (a.b).c
 * 
 *      a.d()
 * 
 *      a.(3+3)
 * 
 * }
 * 
 * 
 */