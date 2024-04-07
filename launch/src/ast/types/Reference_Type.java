package ast.types;

public class Reference_Type extends Type {
    private Type innerType;
    
    public Reference_Type(Type t) {
        super(Type_T.REFERENCE);
        this.innerType = t;
    }

    public String toString() {return "(Reference: " + innerType.toString() + ")";}
}
