package ast.sentences;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Sentence implements ASTNode {

    protected int indentation;
    protected int row;

    @Override
    public NodeKind nodeKind() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nodeKind'");
    }

    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }
    
}
