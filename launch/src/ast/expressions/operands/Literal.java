package ast.expressions.operands;

import ast.Josito;
import ast.expressions.Expression;
import exceptions.InvalidDirectionException;

public abstract class Literal extends Expression {
    
    public Literal(int row) {
        this.row = row;
    }
    
    @Override
    public void bind() { /* Nothing to do*/ }

    public abstract boolean equals(Literal other);

    @Override
    public Expression opnd1() { throw new UnsupportedOperationException("Literals does not have operands"); }
    @Override
    public Expression opnd2() { throw new UnsupportedOperationException("Literals does not have operands"); }

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E
        jose.createConst(this.toIntConst());
    }
    @Override
    public void generateAddress(Josito jose) throws Exception {
        throw new InvalidDirectionException("Literals values are not direccionable");
    }

    public abstract int toIntConst();
}
