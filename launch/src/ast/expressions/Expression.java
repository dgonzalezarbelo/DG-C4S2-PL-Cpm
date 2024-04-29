package ast.expressions;

import ast.ASTNode;
import ast.types.Type;
import ast.types.Type.Type_T;

public abstract class Expression implements ASTNode {
    protected int row;
    protected Type type;

    public Expression opnd1() {throw new UnsupportedOperationException("opnd1");}
    public Expression opnd2() {throw new UnsupportedOperationException("opnd2");}
    public String toString() {return "";}
    public Type_T getType() {return this.type.getKind();};

    @Override
    public void propagateIndentation(int indent) {
        
    }
}
