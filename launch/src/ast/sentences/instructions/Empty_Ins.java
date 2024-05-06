package ast.sentences.instructions;

import ast.Utils;

public class Empty_Ins extends Instruction {

    public Empty_Ins(int row) {
        super(null, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("<empty_ins>" + '\n');
        return str.toString();
    }
}
