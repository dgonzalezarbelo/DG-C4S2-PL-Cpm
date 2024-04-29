package ast.sentences.instructions;

import ast.sentences.Block;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.BooleanConditionException;
import ast.Utils;
import ast.expressions.Expression;
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

	@Override
	public Type checkType() throws Exception {
		try {
            if (argExpression != null && argExpression.checkType().getKind() != Type_T.BOOL) {
                throw new BooleanConditionException("While condition must be bool type");
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        body.checkType();
        return null;
	}
    
}
