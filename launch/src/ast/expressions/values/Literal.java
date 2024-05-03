package ast.expressions.values;

import ast.expressions.Expression;
import ast.types.Type;

public abstract class Literal extends Expression {
    
    public Literal(int row) {
        this.row = row;
    }
    
    public abstract String toString();
    
    @Override
    public void bind() {
        // Nothing to do
    }

    @Override
    public Type checkType() {
        return this.type;
    }
    
    @Override
    public Type getType() {
        return this.type;
    }

    public abstract boolean equals(Literal other);
}
