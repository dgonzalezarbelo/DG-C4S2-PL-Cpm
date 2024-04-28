package ast.types;

import ast.Expression;

public class Bool_Value extends Expression {
    private boolean v;

    public Bool_Value(boolean v, int row) {
        this.v = v;
        this.row = row;
        this.type = new Bool_Type(row);
    }
    
    public boolean value() {return v;}
    public String toString() {return Boolean.toString(v);}

    @Override
    public void bind() {
        // Nothing to do
    }
    @Override
    public void checkType() {
        // Nothing to do
    }  
}
