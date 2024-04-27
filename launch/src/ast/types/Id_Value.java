package ast.types;

import ast.Expression;
import ast.KindE;

public class Id_Value extends Expression {
  private String v;
  public Id_Value(String v) {
    this.v = v;   
  }
  public String getValue() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;}
  
  @Override
  public void bind() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'bind'");
  }  
}
