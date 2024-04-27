package ast.types;

import ast.Expression;
import ast.KindE;

public class Bool_Value extends Expression {
    private boolean v;
    public Bool_Value(boolean v, int row) {
        this.v = v;
        this.row = row;
    }
    public boolean value() {return v;}
    public KindE kind() {return KindE.NUM;}
    public String toString() {return Boolean.toString(v);}

    @Override
    public void bind() {
        // Nothing to do
    }  
}
