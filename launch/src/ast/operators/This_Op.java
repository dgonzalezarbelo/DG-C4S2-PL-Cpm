package ast.operators;

import ast.EUnary;
import ast.KindE;
import ast.types.Id_Value;

public class This_Op extends EUnary {
    public This_Op(String opnd, int row) {
        super(new Id_Value(opnd, row), row);
    }
    public KindE kind() {return KindE.SUMA;}
    public String toString() {return "dis." + opnd1().toString();}

     @Override
    public void bind() {
        opnd1().bind();
    }
}
