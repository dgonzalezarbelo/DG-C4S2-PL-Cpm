package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.KindE;

public class Switch_Ins extends Instruction {

    public Switch_Ins(Expression cond, Block body) {
        super(cond, body);
    }

    public String toString() {return "condition: " + argExpression.toString() ;}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
