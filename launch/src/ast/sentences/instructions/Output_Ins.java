package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;
import ast.Utils;

public class Output_Ins extends Instruction {

    public Output_Ins(Expression expression, int row) {
        super(expression, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("printing " + argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }

    @Override
    public void bind() {
        this.argExpression.bind();
    }
    
}