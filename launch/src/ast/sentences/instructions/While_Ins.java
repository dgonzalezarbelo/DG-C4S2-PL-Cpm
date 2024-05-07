package ast.sentences.instructions;

import ast.sentences.Block;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.BooleanConditionException;
import ast.Josito;
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
        super.bind();
        Program.symbolsTable.closeScope();
    }

	@Override
	public void checkType() throws Exception {
        try {
            super.checkType();
            Type condType = argExpression.getType();
            if (condType.getKind() == null || condType.getKind() != Type_T.BOOL)
                throw new BooleanConditionException("'If' condition must be bolean type");
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}

    @Override
    public void generateCode(Josito jose) { 
        jose.loopInit();
        argExpression.generateValue(jose);
        jose.eqZero();
        jose.conditionalJump(1);
        body.generateCode(jose);
        jose.jump(0);
        jose.endBlock();
        jose.endBlock();
    }
}