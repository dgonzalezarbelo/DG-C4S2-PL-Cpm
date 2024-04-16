package ast.preamble;

import ast.ASTNode;
import ast.NodeKind;
import ast.types.Id_Type;

public abstract class Definition implements ASTNode {
    protected Id_Type id;
    protected Integer indentation;

    public Definition(String name) {
        this.id = new Id_Type(name);
        this.indentation = null;
    }
    
    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public abstract String toString();
}