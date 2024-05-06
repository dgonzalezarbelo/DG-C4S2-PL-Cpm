package ast;

public abstract class ASTNode {
    protected int row;
    protected Integer maximumMemory;

    public int getRow() {
        return row;
    }
    
    public abstract String toString();
    
    public abstract void bind();
    public abstract void checkType() throws Exception;
    public abstract void maxMemory(Integer c, Integer maxi); // { // Nothing to do }
    public void computeOffset(Delta delta) {
        // TODO Esto no debería ser así, sino abstracta (probablemente)
    }
    // public ?? generateCode() // for the future
    
}