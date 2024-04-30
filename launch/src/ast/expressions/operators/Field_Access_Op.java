package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;

public class Field_Access_Op extends EBin {
    public Field_Access_Op(Expression opnd1, Expression opnd2, int row) { // TODO hay que cambiar opnd2 para que no sea un Expression y sea solo o llamada a funcion o variable
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "." + opnd2().toString(); };

    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type leftSide = opnd1().checkType();
        if (leftSide.getKind() != Type_T.CLASS && leftSide.getKind() != Type_T.STRUCT)
            throw new InvalidTypeException("Field access operator '.' only applicable to classes or structs");
        if (rightSide)
        leftSide.tienesNombre(rightSide);
    }
}