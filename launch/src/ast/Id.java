package ast;

public class Id extends Expression {
  private String v;
  public Id(String v) {
    this.v = v;   
  }
  public String value() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return v;}  
}
