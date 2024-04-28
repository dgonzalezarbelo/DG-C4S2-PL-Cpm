package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.sentences.Sentence;

public abstract class Instruction extends Sentence {
    protected Expression argExpression;
    protected Block body;

    public Instruction(Expression args, Block ins, int row) {
        this.argExpression = args;
        this.body = ins;
        this.row = row;
    }
    
    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        if (body != null) body.propagateIndentation(indent + 1);
    }

    public String toString() {return "";}
}
