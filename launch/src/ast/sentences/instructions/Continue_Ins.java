package ast.sentences.instructions;

import ast.KindE;
import ast.Utils;

public class Continue_Ins extends Instruction {

    public Continue_Ins() {
        super(null, null);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("continue" + '\n');
        return str.toString();
    }

    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }

    @Override
    public void bind() {
        
    }
    
}
