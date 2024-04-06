package ast.operators;

import ast.EUnary;
import ast.Expression;
import ast.KindE;

public class Minus_Op extends EUnary {
   public Minus_Op(Expression opnd) {
     super(opnd);  
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return "-" + opnd1().toString();}
}
