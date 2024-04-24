package ast.preamble;

import java.util.List;

import ast.Utils;
import ast.sentences.declarations.Declaration;

public class Struct extends Definition {
    private List<Declaration> atributes;
    private List<Function> constructors;

    public Struct(String name, List<Declaration> atributes, List<Function> constructors) {
        super(name);
        this.atributes = atributes;
        this.constructors = constructors;
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        else
            this.propagateIndentation(this.indentation);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Struct: " + id + "\n");
        for (Declaration i : atributes) {
            Utils.appendIndent(str, indentation + 1);
            str.append("Atributo: " + i.toString() + "\n");
        }
        for (Function f : constructors) {
            Utils.appendIndent(str, indentation + 1);
            str.append("Constructor: " + f.toString() + "\n");
        }
        return str.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        for (Declaration d : this.atributes)
            d.propagateIndentation(indent + 1);
        for (Function f : this.constructors)
            f.propagateIndentation(indent + 1);
    }        
}