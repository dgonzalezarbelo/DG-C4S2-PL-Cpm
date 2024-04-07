package ast.preamble;

import ast.Expression;

public class Define extends Definition {
    
    private Expression expression;

    public Define(String id, Expression expression) {
        super(id);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "typedef " + id + expression.toString();
    }        
}
