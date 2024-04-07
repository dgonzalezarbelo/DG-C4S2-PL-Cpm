package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.KindE;

public class If_Ins extends Instruction {
    Block elseBody;

    public If_Ins(Expression cond, Block if_body, Block else_body) {
        super(cond, if_body);
        this.elseBody = else_body;
    }

    public String toString() {return "condition: " + this.argExpression.toString();}

    @Override
    public KindE kind() {
       throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
