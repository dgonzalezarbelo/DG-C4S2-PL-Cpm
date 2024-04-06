package ast.types;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Type implements ASTNode {

  protected enum Type_T {
    INT, BOOL, ARRAY, CLASS, STRUCT, POINTER;

    public String toString() {
      return "Tipo: " + this.name();
    }
  }

  protected Type_T tipo;
  
  public Type(Type_T v) {
    this.tipo = v;   
  }

  public String toString() {
    return this.tipo.toString();
  }

  @Override
  public NodeKind nodeKind() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'nodeKind'");
  }
}
