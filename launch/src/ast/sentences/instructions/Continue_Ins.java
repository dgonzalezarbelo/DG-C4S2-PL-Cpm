package ast.sentences.instructions;

import ast.KindE;

public class Continue_Ins extends Instruction {

    public Continue_Ins() {
        super(null, null);
    }

    public String toString() {return "continue";}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
