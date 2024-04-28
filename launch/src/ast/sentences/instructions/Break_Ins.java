package ast.sentences.instructions;

import ast.Utils;

public class Break_Ins extends Instruction {

    public Break_Ins(int row) {
        super(null, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("break" + '\n');
        return str.toString();
    }

    @Override
    public void bind() {
        // Nothing to do
    }
    
}
