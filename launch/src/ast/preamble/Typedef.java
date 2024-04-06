package ast.preamble;

public class Typedef extends Definition {
    private String type2;
    
    public Typedef(String type1, String type2) {
        super(type1);
        this.type2 = type2;
    }

    @Override
    public String toString() {
        return "typedef " + name + type2;
    }        
}
