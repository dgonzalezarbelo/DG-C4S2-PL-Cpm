package ast.types;

public class Reference_Type extends Type { // TODO Eliminar clase y arregla la gramatica acorde
    private Type innerType;
    
    public Reference_Type(Type t, int row) {
        super(Type_T.POINTER, row);
        this.innerType = t;
    }

    public String toString() {return "(Reference: " + innerType.toString() + ")";}

    @Override
    public void bind() {
        innerType.bind();
    }
}
