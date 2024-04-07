package ast.types;

import ast.Expression;
import ast.KindE;

public class Id_Value extends Expression {
  private String v;
  public Id_Value(String v) {
    this.v = v;   
  }
  public String value() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;}  
}
