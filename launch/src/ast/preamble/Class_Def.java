package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.DuplicateDefinitionException;

public class Class_Def extends Definition {
    private List<Attribute> attributes;
    private ClassFunctions functions;

    public Class_Def(String name, List<Attribute> atributes, ClassFunctions functions, int row) {
        super(name, row);
        this.attributes = atributes;
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
        for (Attribute i : attributes)
            str.append(i.toString());
        for (Constructor f : functions.getConstructors())
            str.append('\n' + f.toString());
        for (Method f : functions.getMethods())
            str.append('\n' + f.toString());
        return str.toString();
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        for (Constructor c : functions.getConstructors())
            list.add(c);
        return list;
    }

    @Override
    public void bind() {
        Program.symbolsTable.setCurrentDefinition(this.id);
        Program.symbolsTable.newScope();
        try {
            Program.symbolsTable.insertDefinitions(this.id, this);
            for (Attribute d : attributes)
                d.bind();
            
            for (Constructor c : functions.getConstructors()) {
                if (c.getId() != this.id) {
                    System.out.println("The constructor's name doesn't match the name of the class");
                    continue;
                }
                c.bind();
            }
            
            for (Method m : functions.getMethods())
                m.bind();
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        Program.symbolsTable.closeScope();
        Program.symbolsTable.setCurrentDefinition(""); // empty String to represent that we are outside the class
    }
    
    /*
    class Clase {
        void fun(int a, bool b) {

        }

        int fun(int a, int b) {
            return 1;
        }
    }
    */

    @Override
    public Type checkType() throws Exception { // TODO revisar aqui la sobrecarga de funciones
        for (Attribute a : attributes)
            a.checkType();
        for (Constructor c : functions.getConstructors())
            c.checkType();
        for (Method f : functions.getMethods())
            f.checkType();
        functions.checkForDuplicates(); // We check if the constructors and methods are well defined or if there are duplicates (in which case we remove the duplicated definitions)
        return null;
    }

    @Override
    public Type_T checkKind() {
        return Type_T.CLASS;
    }

    @Override
    public Attribute hasAttribute(FieldID name) {
        for (Attribute a : attributes)
            if (a.getName().equals(name))
                return a;
        return null;
    }

    @Override 
    public Method hasMethod(MethodCall m) {
        List<Function> casting = new ArrayList<>();
        for (Method met : functions.getMethods())
            casting.add((Function)met);
        Function f = m.matchWith(casting);
        if (f != null)
            return (Method) f;
        return null;
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        for (Attribute a : this.attributes)
            a.propagateIndentation(indent + 1);
        this.functions.propagateIndentation(indent + 1);
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