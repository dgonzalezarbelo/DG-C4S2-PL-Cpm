package ast.sentences.instructions;

import ast.sentences.Block;
import ast.KindE;

public class Default_Ins extends Instruction {

    public Default_Ins(Block body) {
        super(null, body);
    }

    public String toString() {return "default" ;}

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
