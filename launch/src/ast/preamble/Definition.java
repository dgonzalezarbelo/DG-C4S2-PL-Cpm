package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.NodeKind;
import ast.types.Id_Type;

public abstract class Definition implements ASTNode {
    protected Id_Type id;
    protected Integer indentation;
    protected int row;
    
    public Definition(String name, int row) {
        this.id = new Id_Type(name, row);
        this.indentation = null;
        this.row = row;
    }
    
    public Id_Type getId() {
        return this.id;
    }

    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public abstract String toString();
    public abstract List<ASTNode> getReferences();
}