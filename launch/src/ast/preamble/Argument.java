package ast.preamble;

import ast.Josito;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Type;
import utils.GoodInteger;
import utils.Utils;

public class Argument extends Declaration {
    boolean isReference;

    public Argument(Type type, String id, int row) {
        super(type, id, row);
        isReference = false;
    } 

    public Argument(Type type, String id, boolean isReference, int row) {
        super(type, id, row);
        this.isReference = isReference;
    } 

    public Argument(Declaration dec) {
        super(dec);
    }

    public boolean isReference() {
        return isReference;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(this.type.toString()
        + " " + varname.toString() + '\n');
        return str.toString();
    }
    
    @Override
    public void maxMemory(GoodInteger c, GoodInteger max) {
        if (isReference) {
            maximumMemory.setValue(4);              // If its a reference its the pointer Size
            c.setValue(c.toInt() + 4);
        }
        else
            super.maxMemory(c, max);
    }

    @Override
    public void generateCode(Josito jose) { // TODO
        this.type.generateCode(jose);
    }
}


