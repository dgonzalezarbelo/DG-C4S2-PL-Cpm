package ast.preamble;

import ast.ASTNode;
import ast.NodeKind;
import ast.types.Id;

public abstract class Definition implements ASTNode {
    protected Id id;

    

    public Definition(String name) {
        this.id = new Id(name);
    }

    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public abstract String toString();
}