package ast.operators;

import ast.EUnary;
import ast.Expression;
import ast.KindE;

public class Reference_Op extends EUnary {
    public Reference_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    public KindE kind() {return KindE.SUMA;}
    public String toString() {return "&" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
    }
}