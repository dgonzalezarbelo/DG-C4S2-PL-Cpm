package ast;

public class This_Op extends EUnary {
   public This_Op(Expression opnd) {
     super(opnd);
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return "dis." + opnd1().toString();}
}
