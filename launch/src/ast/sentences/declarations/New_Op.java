package ast.sentences.declarations;

import ast.EUnary;
import ast.Expression;
import ast.KindE;

public class New_Op extends EUnary {
   public New_Op(Expression opnd) {
     super(opnd);
   }     
   public String toString() {return "dis." + opnd1().toString();}
    @Override
    public KindE kind() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
}
