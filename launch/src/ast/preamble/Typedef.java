package ast.preamble;
import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
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
			Program.symbolsTable.copyDefinition(this.id, type.getName(), this);
		} catch (DuplicateDefinitionException e) {
			System.out.println(e);
		}
    }

    @Override
    public List<ASTNode> getConstructors() {
        List<ASTNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public Type getRootType() {
        this.root_definition = type.getRootType();
        return this.root_definition;
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
    public Attribute hasAttribute(FieldID name, boolean insideClass) throws Exception {
        return ((Defined_Type) root_definition).hasAttribute(name, insideClass);
    }

    @Override
    public Type checkType() throws Exception {
        return root_definition;
    }

    @Override
    public String getName() {
        return this.type.getName();
    }

    @Override
    public void maxMemory(Integer c, Integer max) {
        root_definition.maxMemory(c, max);
    }

    @Override
    public Integer getSize() {
        return root_definition.getSize();
    }
}

//FIXME
/*
taipdef int i
taipdef i entero
 */