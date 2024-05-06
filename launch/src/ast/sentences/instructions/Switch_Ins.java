package ast.sentences.instructions;

import exceptions.InvalidTypeException;
import exceptions.MatchingTypeException;

import java.util.List;

import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;

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
	public void checkType() throws Exception {
        super.checkType();
        for(Case_Ins clause : clauses)
            clause.checkType();
        default_Ins.checkType();

        Type condType;        
		try {
            // Condition check
            condType = argExpression.getType();
            if (condType == null || (condType.getKind() != Type_T.BOOL && condType.getKind() != Type_T.INT))
                throw new InvalidTypeException(String.format("Switch condition must be '%s' type or int '%s'", Type_T.BOOL.name(), Type_T.INT.name()));
            
            // Matching cases with condition check
            Type clauseType;
            for(Case_Ins clause : clauses) {
                clauseType = clause.getType();
                if(clauseType.getKind() != condType.getKind())
                    throw new MatchingTypeException("Case doesn't match the type in the Switch condition");
            }
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}
}
