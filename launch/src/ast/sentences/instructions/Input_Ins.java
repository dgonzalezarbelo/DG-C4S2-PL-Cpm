package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;

public class Input_Ins extends Instruction {

    public Input_Ins(Expression expression) {
        super(expression, null);
    }

    public String toString() {return "reading " + this.argExpression.toString();}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}