package ast.types;

public class Int_Type extends Type {
    private static final String NAME = "int";
    public Int_Type(int row) {
        super(Type_T.INT, row);        
    }

    @Override
    public void bind() {
        // Nothing to do
    }

    @Override
    public String getName() {
        return NAME;
    }     
}
