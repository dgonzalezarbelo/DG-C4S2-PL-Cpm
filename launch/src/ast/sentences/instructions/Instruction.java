package ast.sentences.instructions;

import ast.sentences.Block;
import ast.Expression;
import ast.KindE;
import ast.NodeKind;
import ast.sentences.Sentence;

public abstract class Instruction extends Sentence {
    protected Expression argExpression;
    protected Block body;

    public Instruction(Expression args, Block ins) {
        this.argExpression = args;
        this.body = ins;
    }

    @Override
    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    
    public abstract KindE kind();
    public String toString() {return "";}
}
