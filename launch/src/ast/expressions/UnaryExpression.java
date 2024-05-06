package ast.expressions;

public abstract class UnaryExpression extends Expression {
    private Expression opnd;
    
    public UnaryExpression(Expression opnd, int row) {
        this.opnd = opnd;
        this.row = row;
    }

    @Override
    public Expression opnd1() {return this.opnd;}
    @Override
    public Expression opnd2() { throw new UnsupportedOperationException("Unary expressions does not have right operand");}

    @Override
    public void checkType() throws Exception {
        this.opnd.checkType();        
    }
}
