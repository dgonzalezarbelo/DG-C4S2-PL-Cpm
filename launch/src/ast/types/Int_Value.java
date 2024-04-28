package ast.types;

import ast.Expression;

public class Int_Value extends Expression {
    private int v;

    public Int_Value(String v, int row) {
        this.v = Integer.parseInt(v);   
        this.row = row;
        this.type = new Int_Type(row);
    }
    public int num() {return v;}
    public String toString() {return Integer.toString(v);}
    
    @Override
    public void bind() {
        // Nothing to do
    }  
    @Override
    public void checkType() {
        // Nothing to do
    } 
}
