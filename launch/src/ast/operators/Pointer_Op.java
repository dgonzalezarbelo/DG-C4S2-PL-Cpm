package ast.operators;

import ast.EUnary;
import ast.Expression;


public class Pointer_Op extends EUnary {
    public Pointer_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "~" + opnd1().toString();}

    @Override
    public void bind() {
        opnd1().bind();
    }
}
