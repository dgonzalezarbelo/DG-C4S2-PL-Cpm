package ast;

public abstract class ASTNode {
    protected int row;

    public int getRow() {
        return row;
    }
    
    public abstract String toString();
    
    public abstract void bind();
    public abstract void checkType() throws Exception;
    // public ?? generateCode() // for the future
    
}