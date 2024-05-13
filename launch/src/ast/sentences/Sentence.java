package ast.sentences;

import ast.ASTNodeTypable;
import ast.Indentable;

public abstract class Sentence extends ASTNodeTypable implements Indentable {
    protected int indentation;

    public Sentence() {
        this.type = null;
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }
}
