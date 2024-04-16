package ast.types;

public class Pointer_Type extends Type {
    private Type innerType;
    
    public Pointer_Type(Type type) {
        super(Type_T.POINTER); 
        this.innerType = type;       
    }

    public String toString() {return "~" + innerType.toString();}
}
