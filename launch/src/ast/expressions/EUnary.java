package ast.expressions;

public abstract class EUnary extends Expression {
    private Expression opnd;
    
    public EUnary(Expression opnd, int row) {
        this.opnd = opnd;
        this.row = row;
    }
    public Expression opnd1() {return this.opnd;}
    public Expression opnd2() {return null;}    
}
