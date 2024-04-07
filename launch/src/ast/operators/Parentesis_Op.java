package ast.operators;

import java.util.List;

import ast.Expression;
import ast.KindE;

public class Parentesis_Op extends Expression {
  private Expression id;
  private List<Expression> args;
  public Parentesis_Op(Expression opnd1, List<Expression> opnd2) {
    this.id = opnd1;
    this.args = opnd2;
  }     
  public KindE kind() {return KindE.SUMA;}
  public String toString() {return opnd1().toString() + " + " + opnd2().toString();}
}
