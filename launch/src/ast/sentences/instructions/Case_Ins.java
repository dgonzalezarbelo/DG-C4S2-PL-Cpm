package ast.sentences.instructions;

import ast.Expression;
import ast.Utils;
import ast.preamble.Program;
import ast.sentences.Block;

public class Case_Ins extends Instruction {

    public Case_Ins(Expression cond, Block body, int row) {
        super(cond, body, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("case: " + argExpression.toString() + '\n');
        str.append(body.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        argExpression.bind(); // argExpression should be a "constant" and the binding wont change anything
        body.bind();
        Program.symbolsTable.closeScope();
    }
    
}
