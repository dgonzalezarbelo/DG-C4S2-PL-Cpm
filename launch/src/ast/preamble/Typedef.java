package ast.preamble;
import ast.sentences.declarations.Declaration;
import ast.types.Type;

public class Typedef extends Definition {
    private Type type;
    
    public Typedef(Declaration dec) {
        super(dec.getId().toString());
        this.type = dec.getType();
    }

    @Override
    public String toString() {
        return "typedef " + type.toString() + id.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }        
}
