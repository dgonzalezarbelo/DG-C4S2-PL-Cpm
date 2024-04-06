package ast.sentences.declarations;

import ast.sentences.Sentence;
import ast.types.Id;
import ast.types.Type;

public class Declaration extends Sentence {
    private Type type;
    private Id id;
    // private Visibility vis;
    // private Boolean isReference; 
    // TODO

    public Declaration(Type type, Id id) {
        this.type = type;
        this.id = id;
    }

    public String toString() {return this.type.toString() + " " + id.toString() ;};
}