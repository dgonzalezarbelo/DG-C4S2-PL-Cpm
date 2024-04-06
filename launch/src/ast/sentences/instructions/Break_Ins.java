package ast.sentences.instructions;

import ast.KindE;

public class Break_Ins extends Instruction {

    public Break_Ins() {
        super(null, null);
    }

    public String toString() {return "break" ;}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
