package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.sentences.declarations.Declaration;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.DuplicateDefinitionException;

public class Struct extends Definition {
    private List<Attribute> attributes;
    private ClassFunctions functions;

    public Struct(String name, List<Attribute> atributes, List<Constructor> constructors, int row) {
        super(name, row);
        this.attributes = atributes;
        this.functions = new ClassFunctions(constructors, new ArrayList<>());
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
        for (Attribute i : attributes) {
            str.append(i.toString() + "\n");
        }
        for (Constructor f : functions.getConstructors()) {
            str.append("\n" + f.toString());
        }
        return str.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        for (Attribute d : attributes)
            d.propagateIndentation(indent + 1);
        for (Constructor f : functions.getConstructors())
            f.propagateIndentation(indent + 1);
    }

    @Override
    public void bind() {
        Program.symbolsTable.setCurrentDefinition(this.id);
        Program.symbolsTable.newScope();

        try {
            Program.symbolsTable.insertDefinitions(this.id, this);
            for (Declaration d : attributes)
            d.bind();
            
            for (Constructor c : functions.getConstructors()) {
                if (c.getId() != this.id) {
                    System.out.println("The constructor's name doesn't match the name of the class");
                    continue;
                }
                c.bind();
            }
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }

        Program.symbolsTable.closeScope();
        Program.symbolsTable.setCurrentDefinition("");
    }

    @Override
    public Type checkType() throws Exception {
        for (Attribute a : attributes)
            a.checkType();
        for (Constructor c : functions.getConstructors())
            c.checkType();
        functions.checkForDuplicates(); // We check if the constructors are well defined or if there are duplicates (in which case we remove the duplicated definitions)
        return null;
    }

    public Type_T checkKind() {
        return Type_T.STRUCT;
    }

    @Override
    public Attribute hasAttribute(String name) {
        for (Attribute a : attributes)
            if (a.getName().equals(name))
                return a;
        return null;
    }

    @Override
    public Method hasMethod(MethodCall mc) {
        return null;
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        for (Constructor c : functions.getConstructors())
            list.add(c);
        return list;
    }        
}