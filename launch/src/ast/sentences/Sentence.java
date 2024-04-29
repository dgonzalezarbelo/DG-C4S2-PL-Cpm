package ast.sentences;

import ast.ASTNode;

public abstract class Sentence implements ASTNode {

    protected int indentation;
    protected int row;

    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

    public int getRow() {
        return row;
    }
    
}
