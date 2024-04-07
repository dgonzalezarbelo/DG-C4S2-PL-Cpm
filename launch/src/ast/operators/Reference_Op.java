package ast.operators;

import ast.EUnary;
import ast.Expression;
import ast.KindE;

public class Reference_Op extends EUnary {
   public Reference_Op(Expression opnd) {
     super(opnd);  
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return "&" + opnd1().toString();}
}