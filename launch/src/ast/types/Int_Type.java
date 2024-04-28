package ast.types;

public class Int_Type extends Type {
    public Int_Type(int row) {
        super(Type_T.INT, row);        
    }

    @Override
    public void bind() {
        // Nothing to do
    }    

    @Override
    public void checkType() {
        // Nothing to do
    }    
}
