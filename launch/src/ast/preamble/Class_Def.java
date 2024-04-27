package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.sentences.declarations.Declaration;
import exceptions.DuplicateDefinitionException;

public class Class_Def extends Definition {
    private List<Declaration> atributes;
    private ClassFunctions functions;

    public Class_Def(String name, List<Declaration> atributes, ClassFunctions functions, int row) {
        super(name, row);
        this.atributes = atributes;
        this.functions = functions;
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        else
            this.propagateIndentation(this.indentation);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Class: " + id + "\n");
        for (Declaration i : atributes)
            str.append(i.toString());
        for (Function f : functions.getConstructors())
            str.append('\n' + f.toString());
        for (Function f : functions.getMethods())
            str.append('\n' + f.toString());
        return str.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        for (Declaration a : this.atributes)
            a.propagateIndentation(indent + 1);
        this.functions.propagateIndentation(indent + 1);
    }

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        try {
            Program.symbolsTable.insertDefinitions(this.id, this);
            for (Declaration d : atributes)
                d.bind();
            
            for (Constructor c : functions.getConstructors()) {
                if (c.getId() != this.id) {
                    System.out.println("The constructor's name doesn't match the name of the class");
                    continue;
                }
                c.bind();
            }
    
            for (Function m : functions.getMethods())
                m.bind();
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        Program.symbolsTable.closeScope();
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        for (Constructor c : functions.getConstructors())
            list.add(c);
        return list;
    }
}

/*
FIXME

typedef long long ll
typedef ll larguito
typedef larguito hola
typedef hola holita

clas Alumno {
    holita a;
    larguito d;
    ll b;
    hola c;
}

func mein(): int {
    int Alumno;
    int Alumno = 0;
    ll a = 0;
    int ll = 0;
    Alumno a = 0;
    Alumno a = new Alumno(1);
    if(true) {
        Alumno a = new Alumno(2);
    }
    return 0;
}


int a =  0;
if (...) {
    int a = 1;
    ceaut(a);
}
*/