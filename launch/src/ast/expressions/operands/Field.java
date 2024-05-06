package ast.expressions.operands;

import ast.expressions.Expression;
import ast.types.interfaces.Defined_Type;

public abstract class Field extends Expression {
    protected String fieldname;
    protected Defined_Type classFrom;
    protected boolean accessingFromInside;

    public Field(String fieldname, int row) {
        this.fieldname = fieldname;
        this.row = row;
    }

    public void setClassFrom(Defined_Type leftType, boolean isThisLeft) {
        this.classFrom = leftType;
        this.accessingFromInside = isThisLeft;
    }

    @Override
    public Expression opnd1() {
        throw new UnsupportedOperationException("Unimplemented method 'opnd1'");
    }

    @Override
    public Expression opnd2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opnd2'");
    }
}
