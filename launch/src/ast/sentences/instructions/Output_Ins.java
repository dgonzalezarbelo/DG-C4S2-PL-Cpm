package ast.sentences.instructions;

import ast.Expression;
import ast.KindE;

public class Output_Ins extends Instruction {

    public Output_Ins(Expression expression) {
        super(expression, null);
    }

    public String toString() {return "printing " + argExpression.toString();}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}