package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;
import exceptions.MatchingTypeException;
import exceptions.UnexpectedTypeException;
import ast.types.Bool_Type;
import ast.types.Type;
import ast.types.Type.Type_T;


public class Or_Op extends EBin {
    public Or_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
        this.type = new Bool_Type(row);
    }
    
    public String toString() {return opnd1().toString() + " or " +opnd2().toString();}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type left = opnd1().checkType();
        Type right = opnd2().checkType();
        if (left.getKind() != right.getKind())
            throw new MatchingTypeException(String.format("'or' operands '%s' and '%s' do not have the same type at row %d", opnd1().toString(), opnd2().toString(), this.row));
        if (left.getKind() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected (right) but " + left.getKind().name() + " was read at row " + this.row);
        return type;
    }
}
