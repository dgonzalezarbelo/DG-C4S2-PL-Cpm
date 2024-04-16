package ast.preamble;

import java.util.List;

import ast.Utils;
import ast.sentences.declarations.Declaration;

public class Class_Def extends Definition {
    private List<Declaration> atributes;
    private ClassFunctions functions;

    public Class_Def(String name, List<Declaration> atributes, ClassFunctions functions) {
        super(name);
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
}
