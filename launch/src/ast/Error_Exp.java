package ast;

import ast.expressions.Expression;

public class Error_Exp extends Expression {
    
    public Error_Exp() {
        
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        // Utils.appendIndent(str, indentation);
        str.append("ERROR EXP\n");
        return str.toString();
    }

    @Override
    public void bind() {
        // Nothing to do
    }

    @Override
    public void checkType() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkType'");
    }
}
