package ast.operators;

import java.util.List;

import ast.Expression;
import ast.KindE;
import ast.types.Id_Value;

public class Parentesis_Op extends Expression {
  private Id_Value id;
  private List<Expression> args;
  public Parentesis_Op(String opnd1, List<Expression> opnd2) {
    this.id = new Id_Value(opnd1);
    this.args = opnd2;
  }     
  public KindE kind() {return KindE.SUMA;}
  public String toString() {return id.toString() + ": " + args.toString();}
}
