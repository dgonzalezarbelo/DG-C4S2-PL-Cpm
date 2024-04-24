package ast.sentences.instructions;

import ast.sentences.Block;
import ast.KindE;
import ast.Utils;

public class Default_Ins extends Instruction {

    public Default_Ins(Block body) {
        super(null, body);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("default" + '\n');
        return str.toString();
    }
    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
