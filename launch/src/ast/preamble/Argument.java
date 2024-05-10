package ast.preamble;

import ast.Josito;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Type;
import utils.Utils;

public class Argument extends Declaration {

    public Argument(Type type, String id, int row) {
        super(type, id, row);
    } 

    public Argument(Declaration dec) {
        super(dec);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(this.type.toString()
        + " " + varname.toString() + '\n');
        return str.toString();
    }

    @Override
    public void generateCode(Josito jose) { // TODO
        this.type.generateCode(jose);
    }
}


