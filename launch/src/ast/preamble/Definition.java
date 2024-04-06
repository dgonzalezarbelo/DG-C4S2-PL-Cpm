package ast.preamble;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Definition implements ASTNode {
    protected String name;

    public Definition(String name) {
        this.name = name;
    }

    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public abstract String toString();
}