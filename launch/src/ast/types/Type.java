package ast.types;

import ast.KindE;

public class Type {
  private String v;
  public Type(String v) {
    this.v = v;   
  }
  public String value() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;}  
}
