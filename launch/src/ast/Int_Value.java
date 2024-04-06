package ast;

public class Int_Value extends Expression {
  private int v;
  public Int_Value(int v) {
    this.v = v;   
  }
  public int num() {return v;}
  public KindE kind() {return KindE.NUM;}   
  public String toString() {return Integer.toString(v);}  
}
