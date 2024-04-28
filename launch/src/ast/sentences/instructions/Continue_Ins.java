package ast.sentences.instructions;

import ast.Utils;

public class Continue_Ins extends Instruction {

    public Continue_Ins(int row) {
        super(null, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("continue" + '\n');
        return str.toString();
    }

    @Override
    public void bind() {
        // Nothing to do
    }
    
}
