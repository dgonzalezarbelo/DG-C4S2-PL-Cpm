package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.sentences.declarations.Declaration;
import exceptions.DuplicateDefinitionException;

public class Struct extends Definition {
    private List<Declaration> atributes;
    private List<Constructor> constructors;

    public Struct(String name, List<Declaration> atributes, List<Constructor> constructors, int row) {
        super(name, row);
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

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        for (Declaration d : atributes)
            d.bind();
            
        for (Constructor c : constructors) {
            if (c.getId().getName() != this.id.getName()) {
                System.out.println("The constructor's name doesn't match the name of the class");
                continue;
            }
            c.bind();
        }
        try {
            Program.symbolsTable.insertDefinitions(this.id.getName(), this);
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }

        Program.symbolsTable.closeScope();
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        for (Constructor c : constructors)
            list.add(c);
        return list;
    }        
}