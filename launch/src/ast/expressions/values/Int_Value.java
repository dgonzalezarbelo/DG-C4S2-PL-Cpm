package ast.expressions.values;

import ast.types.Int_Type;

public class Int_Value extends Literal {
    private int v;

    public Int_Value(String v, int row) {
        super(row);
        this.v = Integer.parseInt(v);   
        this.row = row;
        this.type = new Int_Type(row);
    }
    public int num() {return v;}
    public String toString() {return Integer.toString(v);}
}
