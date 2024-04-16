package ast.sentences.instructions;

import ast.KindE;
import ast.Utils;

public class Break_Ins extends Instruction {

    public Break_Ins() {
        super(null, null);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("break" + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
    
}
