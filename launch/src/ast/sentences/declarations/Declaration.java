package ast.sentences.declarations;

import ast.Utils;
import ast.preamble.Program;
import ast.preamble.Visibility;
import ast.sentences.Sentence;
import ast.types.Array_Type;
import ast.types.VariableID;
import ast.types.Type;
import exceptions.DuplicateDefinitionException;

public class Declaration extends Sentence {
    private Type type;
    private VariableID id;
    private Visibility visibility;          // FIXME igual merece la pena hacer una clase atributo

    public Declaration(Type type, String id, int row) {
        this.type = type;
        this.id = new VariableID(id, row);
        this.visibility = null;
        this.row = row;
    } 

    // Adding visibility to the declaration
    public Declaration(Type type, String id, Visibility visibility, int row) {
        this(type, id, row);
        this.visibility = visibility;
    }

    public void setVisibility(Visibility v) {
        this.visibility = v;
    }

    public VariableID getId() {
        return this.id;
    }

    public Type getType() {
        return this.type;
    }

    public static Declaration manageDeclaration(Type t, String id, Array_Type array, int row) {
        return new Declaration(manageType(t, array), id, row);
    }

    public static Type manageType(Type t, Array_Type array) {
        if (array != null) {
            array.setInnerType(t);
            return array.recoverBiggestArray(t, 1);
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
        type.bind();
        try {
            Program.symbolsTable.insertSymbol(id.getValue(), this);
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}  
}