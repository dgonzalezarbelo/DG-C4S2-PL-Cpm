package ast;

public abstract class EBin extends Expression {
    private Expression opnd1;
    private Expression opnd2;
    
    public EBin(Expression opnd1, Expression opnd2, int row) {
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
        this.row = row;
    }
    public Expression opnd1() {return opnd1;}
    public Expression opnd2() {return opnd2;}
}
