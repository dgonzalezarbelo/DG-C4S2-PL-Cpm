package ast.expressions.values;

import ast.types.Bool_Type;
import ast.types.Const_Type;

public class Bool_Value extends Literal {
    private boolean v;

    public Bool_Value(boolean v, int row) {
        super(row);
        this.v = v;
        this.row = row;
        this.type = new Const_Type(new Bool_Type(row), row, this);
    }
    
    public boolean value() {return v;}
    public String toString() {return Boolean.toString(v);}

    @Override
    public boolean equals(Literal other) {
        if (other.getClass().equals(this.getClass()))
            return this.v == ((Bool_Value) other).v;
        return false;
    }
}
