package ast.types;

import ast.Expression;
import ast.KindE;

public class Int_Value extends Expression {
  private int v;
  public Int_Value(String v) {
    this.v = Integer.parseInt(v);   
  }
  public int num() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return Integer.toString(v);}  
}
