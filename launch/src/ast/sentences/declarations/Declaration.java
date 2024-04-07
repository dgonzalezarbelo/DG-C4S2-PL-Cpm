package ast.sentences.declarations;

import ast.preamble.Visibility;
import ast.sentences.Sentence;
import ast.types.Array_Type;
import ast.types.Id_Type;
import ast.types.Type;

public class Declaration extends Sentence {
    private Type type;
    private Id_Type id;
    private Visibility visibility;

    public Declaration(Type type, String id) {
        this.type = type;
        this.id = new Id_Type(id);
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

    public Id_Type getId() {
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
        return (visibility == null ? "" : visibility.toString())
        + this.type.toString()
        + " " + id.toString();
    }
}