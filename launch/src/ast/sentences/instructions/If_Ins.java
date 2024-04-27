package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.KindE;
import ast.Utils;
import ast.preamble.Program;

public class If_Ins extends Instruction {
    private Block elseBody;

    public If_Ins(Expression cond, Block if_body, Block else_body, int row) {
        super(cond, if_body, row);
        this.elseBody = else_body;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("if (" + this.argExpression.toString() + ")\n");
        str.append(body.toString());
        if(!elseBody.empty()) {
            Utils.appendIndent(str, indentation);
            str.append("else"  + '\n');
            str.append(elseBody.toString());
        }
        return str.toString();
    }
    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        this.body.propagateIndentation(indent + 1);
        this.elseBody.propagateIndentation(indent + 1);
    }

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        this.argExpression.bind();
        this.body.bind();
        Program.symbolsTable.closeScope();
        if (!elseBody.empty()) {
            Program.symbolsTable.newScope();
            elseBody.bind();
            Program.symbolsTable.closeScope();
        }
    }
}
