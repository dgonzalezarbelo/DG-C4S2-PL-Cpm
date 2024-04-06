package ast.types;

public class Array extends Type {
    private int dim;
    private Type tipo;

    public Array(Type_T v) {
        super(v);
        
    }
}
