package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.Type;

public class Expression_Ins extends Instruction {

    public Expression_Ins(Expression expression, int row) {
        super(expression, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public void bind() {
        this.argExpression.bind();
    }

    @Override
    public Type checkType() throws Exception {
        try {
            Type t = argExpression.checkType();
            return t;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
