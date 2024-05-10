package ast.sentences.instructions;

import ast.Josito;
import utils.Utils;

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
    public void generateCode(Josito jose) { 
        /*
         * The continue instruction will skip the current iteration of the 
         * innermost nested block by labelling it with the number 0
         */
        jose.jump(0); 
    }
}
