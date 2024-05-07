package ast.expressions.operands;

import ast.types.interfaces.Bool_Type;
import ast.types.interfaces.Const_Type;

public class Bool_Value extends Literal {
    private boolean v;

    public Bool_Value(boolean v, int row) {
        super(row);
        this.v = v;
    }
    
    public boolean value() {return v;}
    public String toString() {return Boolean.toString(v);}

    @Override
    public void checkType() throws Exception {
        this.type = new Const_Type(new Bool_Type(row), row, this);
    }

    @Override
    public boolean equals(Literal other) {
        if (other.getClass().equals(this.getClass()))
            return this.v == ((Bool_Value) other).v;
        return false;
    }

    @Override
    public int toIntConst() {
        return (v) ? 1 : 0;
    }
}
