package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.Utils;
import ast.preamble.Program;

public class While_Ins extends Instruction {

    public While_Ins(Expression cond, Block body, int row) {
        super(cond, body, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("while(" + this.argExpression.toString() + ")\n");
        str.append(body.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        this.argExpression.bind();
        this.body.bind();
        Program.symbolsTable.closeScope();
    }
    
}
