package ast;

import java.util.List;

public class Id_And_Parentesis extends Expression {
  private Expression id;
  private List<Expression> args;
  public Id_And_Parentesis(Expression opnd1, List<Expression> opnd2) {
    this.id = opnd1;
    this.args = opnd2;
  }     
  public KindE kind() {return KindE.SUMA;}
  public String toString() {return opnd1().toString() + " + " + opnd2().toString();}
}
