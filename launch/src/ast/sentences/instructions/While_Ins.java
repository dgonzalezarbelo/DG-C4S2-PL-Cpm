package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.KindE;

public class While_Ins extends Instruction {

    public While_Ins(Expression cond, Block body) {
        super(cond, body);
    }

    public String toString() {return "condition: " + this.argExpression.toString();}

    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
