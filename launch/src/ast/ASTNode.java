package ast;

import utils.GoodBoolean;
import utils.GoodInteger;

public abstract class ASTNode {
    protected int row;
    protected GoodInteger maximumMemory;
    protected GoodBoolean errorFlag;
    protected SymbolsTable symbolsTable;

    public ASTNode() {
        this.maximumMemory = new GoodInteger(0);
    }

    public int getRow() {
        return row;
    }
    
    public abstract String toString();
    
    public abstract void bind();
    public abstract void checkType() throws Exception;
    public abstract void maxMemory(GoodInteger c, GoodInteger maxi); // { // Nothing to do }
    public abstract void computeOffset(Delta delta);
    public abstract void generateCode(Josito jose);
    
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        this.errorFlag = g;
        this.symbolsTable = s;
    }
    
}