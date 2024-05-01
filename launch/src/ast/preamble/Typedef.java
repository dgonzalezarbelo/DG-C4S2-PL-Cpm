package ast.preamble;
import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.sentences.declarations.Declaration;
import ast.types.Defined_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.DuplicateDefinitionException;
import exceptions.InvalidTypeException;

public class Typedef extends Definition {
    private Type type; // The previously existing type
    private Type root_definition; // The root type of the typedef dependency graph

    public Typedef(Declaration dec, int row) {
        super(dec.getId().toString(), row); // We store the new alias in the id
        this.type = dec.getType_T();
    }

    @Override
    public String toString() {
        return "typedef " + type.toString() + id.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

    @Override
    public void bind() {
        type.bind();
        try {
            Program.symbolsTable.insertDefinitions(this.id, this);
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public Type getRootType() {
        return this.root_definition = type.getRootType();
    }

    @Override
    public Type_T checkKind() {
        return root_definition.getKind();
    }

    @Override   
    public Method hasMethod(MethodCall m) throws Exception {
        if (root_definition.getKind() == Type_T.CLASS)
            return ((Defined_Type) root_definition).hasMethod(m);
        else
            throw new InvalidTypeException("The type " + type.toString() + " don't have any method");
    }

    @Override
    public Attribute hasAttribute(FieldID name) throws Exception {
        return ((Defined_Type) root_definition).hasAttribute(name);
    }

    @Override
    public Type checkType() throws Exception {
        return root_definition;
    }
}

//FIXME
/*
taipdef int i
taipdef i entero
 */