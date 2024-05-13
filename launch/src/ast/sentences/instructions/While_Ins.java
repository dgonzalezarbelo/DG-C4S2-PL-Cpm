package ast.sentences.instructions;

import ast.sentences.Block;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.BooleanConditionException;
import utils.Utils;
import ast.Josito;
import ast.expressions.Expression;

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
        symbolsTable.newScope();
        super.bind();
        symbolsTable.closeScope();
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
            this.errorFlag.setValue(true);
        }
	}

    @Override
    public void generateCode(Josito jose) { 
        jose.loopInit();
        jose.pushBreakJumpScope(1); // Update breakJumpScope pushing a new while break value
        try {
            argExpression.generateValue(jose);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.printErrorRow(row);
        }
        jose.eqZero();
        jose.conditionalJump(1);
        body.generateCode(jose);
        jose.jump(0);
        jose.endBlock();
        jose.endBlock();
        jose.popBreakJumpScope(); // Update breakJumpScope poping the while break value
    }
}