package ast;

public abstract class Expression implements ASTNode {
    protected int row;

    public abstract KindE kind();
    public Expression opnd1() {throw new UnsupportedOperationException("opnd1");} 
    public Expression opnd2() {throw new UnsupportedOperationException("opnd2");} 
    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    public String toString() {return "";}

    @Override
    public void propagateIndentation(int indent) {
        
    }
}
