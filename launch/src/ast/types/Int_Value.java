package ast.types;

import ast.Expression;
import ast.KindE;

public class Int_Value extends Expression {
    private int v;

    public Int_Value(String v, int row) {
        this.v = Integer.parseInt(v);   
        this.row = row;
    }
    public int num() {return v;}
    public KindE kind() {return KindE.NUM;}   
    public String toString() {return Integer.toString(v);}
    
    @Override
    public void bind() {
        // Nothing to do
    }  
}
