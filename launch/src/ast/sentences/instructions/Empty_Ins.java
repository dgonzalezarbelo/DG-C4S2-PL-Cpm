package ast.sentences.instructions;

import ast.KindE;
import ast.Utils;

public class Empty_Ins extends Instruction {

    public Empty_Ins() {
        super(null, null);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("<empty_ins>" + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
