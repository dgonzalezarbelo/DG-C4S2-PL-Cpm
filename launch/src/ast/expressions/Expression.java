package ast.expressions;

import ast.ASTNode;
import ast.types.Type;

public abstract class Expression implements ASTNode {
    protected int row;
    protected Type type;

    public Expression opnd1() {throw new UnsupportedOperationException("opnd1");}
    public Expression opnd2() {throw new UnsupportedOperationException("opnd2");}
    public String toString() {return "";}
    
    @Override
    public Type getType() {return this.type;};

    @Override
    public void propagateIndentation(int indent) {
        
    }
}
