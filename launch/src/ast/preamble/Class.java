package ast.preamble;

import java.util.List;

import ast.sentences.declarations.Declaration;

public class Class extends Definition {
    private List<Declaration> atributes;
    private ClassFunctions functions;

    public Class(String name, List<Declaration> atributes, ClassFunctions functions) {
        super(name);
        this.atributes = atributes;
        this.functions = functions;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Class: " + id + "\n");
        for (Declaration i : atributes)
            str.append("Atributo: " + i.toString() + "\n");
        for (Function f : functions.getConstructors())
            str.append("Constructor: " + f.toString() + "\n");
        for (Function f : functions.getMethods())
            str.append("Method: " + f.toString() + "\n");
        return str.toString();
    }        
}
