package ast;

import ast.types.Type;

public interface ASTNode {
    // public ?? type() // for the future
    // public ?? generateCode() // for the future
    public void bind();
    public Type checkType() throws Exception;
    public String toString();
    public void propagateIndentation(int indent); // To print the AST
}
