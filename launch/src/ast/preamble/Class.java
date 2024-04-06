package ast.preamble;

import java.util.List;

import ast.sentences.declarations.Declaration;

public class Class extends Definition {
    private List<Declaration> atributes;
    private List<Function> methods; //First methodS of the list must be a constructor

    public Class(String name, List<Declaration> atributes, List<Function> methods) {
        super(name);
        this.atributes = atributes;
        this.methods = methods;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Class: " + name + "\n");
        for (Declaration i : atributes)
            str.append("Atributo: " + i.toString() + "\n");
        for (Function f : methods)
            str.append("Method: " + f.toString() + "\n");
        return str.toString();
    }        
}
