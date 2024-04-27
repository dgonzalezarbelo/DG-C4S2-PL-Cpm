package ast.operators;

import ast.EUnary;
import ast.KindE;
import ast.types.Id_Value;

public class This_Op extends EUnary {
   public This_Op(String opnd) {
     super(new Id_Value(opnd));
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return "dis." + opnd1().toString();}

    @Override
   public void bind() {
      opnd1().bind();
    }
}
