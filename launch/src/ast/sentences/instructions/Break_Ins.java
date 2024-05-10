package ast.sentences.instructions;

import ast.Josito;
import utils.Utils;

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
    public void generateCode(Josito jose) { 
        /*
         * The break instruction will exit the current loop of 
         * innermost nested block by labelling it with the number 1
         */

        jose.jump(1); 
    }
}
