package ast.operators;

import ast.EBin;
import ast.Expression;
import ast.KindE;

public class And_Op extends EBin {
   public And_Op(Expression opnd1, Expression opnd2) {
     super(opnd1,opnd2);
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return opnd1().toString() + " an " +opnd2().toString();}
}
