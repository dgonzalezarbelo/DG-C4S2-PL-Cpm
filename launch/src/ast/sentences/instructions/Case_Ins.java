package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;
import ast.Utils;
import ast.sentences.Block;

public class Case_Ins extends Instruction {

    public Case_Ins(Expression cond, Block body) {
        super(cond, body);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("case: " + argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
