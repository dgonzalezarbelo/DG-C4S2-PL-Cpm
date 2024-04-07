package ast.preamble;

import java.util.List;

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
        StringBuilder str = new StringBuilder();
        str.append("Struct: " + id + "\n");
        for (Declaration i : atributes)
            str.append("Atributo: " + i.toString() + "\n");
        for (Function f : constructors)
        str.append("Constructor: " + f.toString() + "\n");
        return str.toString();
    }        
}