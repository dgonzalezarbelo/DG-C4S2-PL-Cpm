package ast;

import ast.types.interfaces.Type;

public abstract class ASTNodeTypable extends ASTNode {
    protected Type type;
    public Type getType() {
        return this.type;
    }
}