package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;


public class Reference_Op extends EUnary {
    public Reference_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "&" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
    }
}