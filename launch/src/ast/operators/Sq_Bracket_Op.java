package ast.operators;

import ast.EBin;
import ast.Expression;


public class Sq_Bracket_Op extends EBin {
    public Sq_Bracket_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "[" + opnd2().toString() + "]";}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }
}
