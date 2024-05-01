package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;
import ast.types.Bool_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.UnexpectedTypeException;

public class And_Op extends EBin {
    public And_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
        this.type = new Bool_Type(row);
    }
    
    public String toString() {return opnd1().toString() + " an " +opnd2().toString();}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public Type checkType() throws Exception {
        opnd1().checkType();
        opnd2().checkType();
        if (opnd1().getType_T() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected (left) but " + opnd1().getType_T().name() + " was read");
        if (opnd2().getType_T() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected (right) but " + opnd1().getType_T().name() + " was read");
        return type;
    }
}
