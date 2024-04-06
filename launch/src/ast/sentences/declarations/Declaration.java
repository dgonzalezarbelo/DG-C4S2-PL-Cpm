package ast.sentences.declarations;

import ast.Type;
import ast.Id;
import ast.sentences.Sentence;

public class Declaration extends Sentence {
    private Type type;
    private Id id;

    public Declaration(Type type, Id id) {
        this.type = type;
        this.id = id;
    }

    public String toString() {return this.type.toString() + " " + id.toString() ;};
}