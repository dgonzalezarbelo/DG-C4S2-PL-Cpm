package ast.preamble;

import java.util.List;

import ast.ASTNode;

public abstract class Definition implements ASTNode {
    protected Integer indentation;
    protected int row;

    protected String id;
    
    public Definition(String name, int row) {
        this.indentation = null;

        this.id = name;
        this.row = row;
    }
    
    public String getId() {
        return this.id;
    }

    public abstract String toString();
    public abstract List<ASTNode> getReferences();
}