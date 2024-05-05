package ast.sentences.instructions;

import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;
import exceptions.MatchingTypeException;

import java.util.List;

import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;

public class Switch_Ins extends Instruction {
    List<Case_Ins> clauses;
    Default_Ins default_Ins;

    public Switch_Ins(Expression cond, List<Case_Ins> clauses, Default_Ins _default, int row) {
        super(cond, null, row);
        this.clauses = clauses;
        this.default_Ins = _default;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("switch( " + argExpression.toString() + ")\n");
        for(Case_Ins clause : clauses)
            str.append(clause.toString());
        str.append(default_Ins.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        this.argExpression.bind();
        for(Case_Ins clause : clauses)
            clause.bind();
        default_Ins.bind();
        Program.symbolsTable.closeScope();
    }

	@Override
	public Type checkType() throws Exception {
        Type_T t = null;
		try {
            t = argExpression.checkType().getKind();
            if (t == null || (t != Type_T.BOOL && t != Type_T.INT))
                throw new InvalidTypeException("Switch in row " + row + " condition must be bool type or int type");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Type clauseType;
            for(Case_Ins clause : clauses) {
                clauseType = clause.checkType();
                if(clauseType.getKind() != t)
                    throw new MatchingTypeException("Case in the row " + clause.getRow() + " doesnt match the type in the Switch condition");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        default_Ins.checkType();
        return null;
	}

    @Override
    public void maxMemory(Integer c, Integer max) {
        for (Case_Ins _case : clauses)
            _case.maxMemory(c, max);
        default_Ins.maxMemory(c, max);
    }
    
}
