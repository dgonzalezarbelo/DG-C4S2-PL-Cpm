package ast.preamble;

public class Define extends Definition {
    
    private String expression;

    public Define(String id, String expression) {
        super(id);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "typedef " + name + expression;
    }        
}
