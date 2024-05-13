package ast.sentences.instructions;

import ast.sentences.Block;
import utils.Utils;
import ast.Josito;

public class Default_Ins extends Instruction {

    public Default_Ins(Block body, int row) {
        super(null, body, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("default" + '\n');
        str.append(body.toString());
        return str.toString();
    }

    @Override
    public void generateCode(Josito jose) { 
        body.generateCode(jose);
    }
}