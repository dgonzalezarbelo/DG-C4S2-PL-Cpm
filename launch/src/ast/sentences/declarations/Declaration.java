package ast.sentences.declarations;

import ast.preamble.Visibility;
import ast.sentences.Sentence;
import ast.types.Array;
import ast.types.Id;
import ast.types.Type;

public class Declaration extends Sentence {
    private Type type;
    private Id id;
    private Visibility visibility;
    // private Boolean isReference; 
    // TODO

    public Declaration(Type type, Id id) {
        this.type = type;
        this.id = id;
        this.visibility = null;
    }

    // Adding visibility to the declarationX
    public Declaration(Type type, Id id, Visibility visibility) {
        this(type, id);
        this.visibility = visibility;
    }

    public void setVisibility(Visibility v) {
        this.visibility = v;
    }

    public Id getId() {
        return this.id;
    }

    public static Declaration manageDeclaration(Type t, Id id, Array array) {
        return new Declaration(manageType(t, array), id);
    }

    public static Type manageType(Type t, Array array) {
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