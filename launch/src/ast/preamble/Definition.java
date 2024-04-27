package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Definition implements ASTNode {
    protected String id;
    protected Integer indentation;
    protected int row;
    
    public Definition(String name, int row) {
        this.id = name;
        this.indentation = null;
        this.row = row;
    }
    
    public String getId() {
        return this.id;
    }

    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public abstract String toString();
    public abstract List<ASTNode> getReferences();
}