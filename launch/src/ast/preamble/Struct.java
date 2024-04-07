package ast.preamble;

import java.util.List;

import ast.sentences.declarations.DeclarationAndAssignation;

public class Struct extends Definition {
    private List<DeclarationAndAssignation> atributes;
    private List<Function> constructors;

    public Struct(String name, List<DeclarationAndAssignation> atributes, List<Function> constructors) {
        super(name);
        this.atributes = atributes;
        this.constructors = constructors;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Struct: " + name + "\n");
        for (DeclarationAndAssignation i : atributes)
            str.append("Atributo: " + i.toString() + "\n");
        for (Function f : constructors)
        str.append("Constructor: " + f.toString() + "\n");
        return str.toString();
    }        
}