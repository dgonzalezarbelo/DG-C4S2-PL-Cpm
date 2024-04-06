package ast.sentences.instructions;

import ast.KindE;

public class Empty_Ins extends Instruction {

    public Empty_Ins() {
        super(null, null);
    }

    public String toString() {return "<empty_ins>" ;}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
