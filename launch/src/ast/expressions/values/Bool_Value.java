package ast.expressions.values;

import ast.types.Bool_Type;

public class Bool_Value extends Literal {
    private boolean v;

    public Bool_Value(boolean v, int row) {
        super(row);
        this.v = v;
        this.row = row;
        this.type = new Bool_Type(row);
    }
    
    public boolean value() {return v;}
    public String toString() {return Boolean.toString(v);}
}
