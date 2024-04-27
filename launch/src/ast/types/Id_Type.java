package ast.types;

public class Id_Type extends Type {
    private String name;

    public Id_Type(String name) {
        super(Type_T.ID);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {return name;}

}
