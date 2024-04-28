package ast;

public interface ASTNode {
    // public ?? generateCode() // for the future
    public void bind();
    public void checkType() throws Exception;
    public String toString();
    public void propagateIndentation(int indent); // To print the AST
}
