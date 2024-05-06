package ast.expressions;

import ast.ASTNodeTypable;
import ast.Indentable;

public abstract class Expression extends ASTNodeTypable implements Indentable {
    protected int indentation;
    public abstract Expression opnd1();
    public abstract Expression opnd2();

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }
}
