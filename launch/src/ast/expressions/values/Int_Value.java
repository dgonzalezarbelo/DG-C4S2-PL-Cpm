package ast.expressions.values;

import ast.types.Const_Type;
import ast.types.Int_Type;

public class Int_Value extends Literal {
    private int v;

    public Int_Value(String v, int row) {
        super(row);
        this.v = Integer.parseInt(v);   
        this.row = row;
        this.type = new Const_Type(new Int_Type(row), row, this);
    }
    public int num() {return v;}
    public String toString() {return Integer.toString(v);}

    @Override
    public boolean equals(Literal other) {
        if (other.getClass().equals(this.getClass()))
            return this.v == ((Int_Value) other).v;
        return false;
    }
}
