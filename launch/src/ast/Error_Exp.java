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
        throw new UnsupportedOperationException("Unimplemented method 'checkType'");
    }

    @Override
    public Expression opnd1() {
        throw new UnsupportedOperationException("Unimplemented method 'opnd1'");
    }

    @Override
    public Expression opnd2() {
        throw new UnsupportedOperationException("Unimplemented method 'opnd2'");
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAddress'");
    }

    @Override
    public void generateValue(Josito jose) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateValue'");
    }
}