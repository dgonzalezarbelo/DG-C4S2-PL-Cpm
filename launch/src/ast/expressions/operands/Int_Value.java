package ast.expressions.operands;

import ast.types.interfaces.Const_Type;
import ast.types.interfaces.Int_Type;

public class Int_Value extends Literal {
    private int v;

    public Int_Value(String v, int row) {
        super(row);
        this.v = Integer.parseInt(v);   
        this.row = row;
    }
    
    public int num() {return v;}
    public String toString() {return Integer.toString(v);}

    @Override
    public boolean equals(Literal other) {
        if (other.getClass().equals(this.getClass()))
            return this.v == ((Int_Value) other).v;
        return false;
    }
    @Override
    public void checkType() throws Exception {
        this.type = new Const_Type(new Int_Type(row), row, this);
    }
    
    @Override
    public int toIntConst() {
        return this.v;
    }
}
