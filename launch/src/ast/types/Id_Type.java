package ast.types;

public class Id_Type extends Type {
    String name;

    public Id_Type(String name) {
        super(Type_T.ID);
        this.name = name;
    }

    public String toString() {return name;}
}
