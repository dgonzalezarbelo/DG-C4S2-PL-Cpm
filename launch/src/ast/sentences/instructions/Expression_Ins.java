package ast.sentences.instructions;

import ast.Josito;
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
        // TODO esto peta cuando llamas solo a una funcion que no devuelve nada
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public void generateCode(Josito jose) {
        try {
            argExpression.generateValue(jose);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.printErrorRow(row);
        }
        if (argExpression.getType() != null)
            jose.consumeTrash();
    }
}
