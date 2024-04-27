package ast.sentences.declarations;

import ast.Utils;
import ast.preamble.Program;
import ast.preamble.Visibility;
import ast.sentences.Sentence;
import ast.types.Array_Type;
import ast.types.Id_Value;
import ast.types.Type;
import exceptions.DuplicateDefinitionException;

public class Declaration extends Sentence {
    private Type type;
    private Id_Value id;
    private Visibility visibility;          // FIXME igual merece la pena hacer una clase atributo

    public Declaration(Type type, String id) {
        this.type = type;
        this.id = new Id_Value(id);
        this.visibility = null;
    } 

    // Adding visibility to the declaration
    public Declaration(Type type, String id, Visibility visibility) {
        this(type, id);
        this.visibility = visibility;
    }

    public void setVisibility(Visibility v) {
        this.visibility = v;
    }

    public Id_Value getId() {
        return this.id;
    }

    public Type getType() {
        return this.type;
    }

    public static Declaration manageDeclaration(Type t, String id, Array_Type array) {
        return new Declaration(manageType(t, array), id);
    }

    public static Type manageType(Type t, Array_Type array) {
        if (array != null) {
            array.propagateType(t);
            return array;
        }
        else
            return t;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append((visibility == null ? "" : visibility.toString() + " " )
        + this.type.toString()
        + " " + id.toString() + '\n');
        return str.toString();
    }

    @Override
	public void bind() {
        try {
            Program.symbolsTable.insertSymbol(id.getValue(), id);
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
        }
	}  
}