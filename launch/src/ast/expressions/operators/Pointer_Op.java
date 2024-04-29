package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;


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
