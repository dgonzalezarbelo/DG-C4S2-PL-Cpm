package ast.preamble;

import ast.Utils;
import ast.expressions.values.VariableID;
import ast.sentences.declarations.Declaration;
import ast.types.Type;

public class Argument extends Declaration {
    private Type type;
    private VariableID id;

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
        + " " + id.toString() + '\n');
        return str.toString();
    }

    @Override
    public Type checkType() throws Exception {
        return this.type;
    }  
}


