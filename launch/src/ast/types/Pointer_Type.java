package ast.types;

public class Pointer_Type extends Type {
    private Type innerType;
    
    public Pointer_Type(Type type, int row) {
        super(Type_T.POINTER, row); 
        this.innerType = type;       
    }

    public String toString() {return "~" + innerType.toString();}

    @Override
    public void bind() {
        innerType.bind();
    }
}
