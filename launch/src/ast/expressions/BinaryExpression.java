package ast.expressions;

public abstract class BinaryExpression extends Expression {
    private Expression opnd1;
    private Expression opnd2;
    
    public BinaryExpression(Expression opnd1, Expression opnd2, int row) {
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
        this.row = row;
    }

    @Override
    public void checkType() throws Exception {
        this.opnd1.checkType();
        this.opnd2.checkType();
    }

    @Override
    public Expression opnd1() {return opnd1;}
    @Override
    public Expression opnd2() {return opnd2;}
}
