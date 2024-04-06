package ast.operators;

import ast.EBin;
import ast.Expression;
import ast.KindE;

public class Greater_Or_Eq_Op extends EBin {
   public Greater_Or_Eq_Op(Expression opnd1, Expression opnd2) {
     super(opnd1,opnd2);  
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return opnd1().toString() + " >= " +opnd2().toString();}
}
