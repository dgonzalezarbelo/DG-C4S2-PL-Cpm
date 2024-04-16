package ast.types;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Type implements ASTNode {

  protected enum Type_T {
    INT, BOOL, ARRAY, CLASS, STRUCT, POINTER, REFERENCE, ID;

    public String toString() {
      return this.name();
    }
  }

  protected Type_T type;
  
  public Type(Type_T v) {
    this.type = v;   
  }

  public String toString() {
    return this.type.toString().toLowerCase();
  }

  @Override
  public NodeKind nodeKind() {
    throw new UnsupportedOperationException("Unimplemented method 'nodeKind'");
  }

  @Override
  public void propagateIndentation(int indent) {
    
  }
}
