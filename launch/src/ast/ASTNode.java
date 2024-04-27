package ast;

public interface ASTNode {
    // public ?? type() // for the future
    // public ?? generateCode() // for the future
    public void bind();
    public NodeKind nodeKind();
    public String toString();
    public void propagateIndentation(int indent);
}
