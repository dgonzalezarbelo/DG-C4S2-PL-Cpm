package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;

public class Field_Access_Op extends EBin {
    public Field_Access_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "." + opnd2().toString(); };

    @Override
    public void bind() {
        opnd1().bind();
    }
}
