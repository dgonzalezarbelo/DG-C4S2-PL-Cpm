package ast.preamble;

import ast.Expression;
import ast.Utils;

public class Define extends Definition {
    
    private Expression expression;

    public Define(String id, Expression expression) {
        super(id);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "define " + id + " " + expression.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }        
}
