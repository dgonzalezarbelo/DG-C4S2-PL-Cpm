package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;

public class Expression_Ins extends Instruction {

    public Expression_Ins(Expression expression, int row) {
        super(expression, null, row);
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        this.type = this.argExpression.getType();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(argExpression.toString() + '\n');
        return str.toString();
    }
}
