package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;
import ast.sentences.Block;

public class Case_Ins extends Instruction {

    public Case_Ins(Expression cond, Block body) {
        super(cond, body);
    }

    public String toString() {return "case: " + argExpression.toString();}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
