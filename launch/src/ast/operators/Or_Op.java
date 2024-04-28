package ast.operators;

import ast.EBin;
import ast.Expression;
import exceptions.UnexpectedTypeException;
import ast.types.Bool_Type;
import ast.types.Type.Type_T;


public class Or_Op extends EBin {
    public Or_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + " or " +opnd2().toString();}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public void checkType() throws Exception {
        opnd1().checkType();
        opnd2().checkType();
        if (opnd1().getType() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected (left) but " + opnd1().getType().name() + " was read");
        if (opnd2().getType() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected (right) but " + opnd1().getType().name() + " was read");
        this.type = new Bool_Type(row);
    }
}
