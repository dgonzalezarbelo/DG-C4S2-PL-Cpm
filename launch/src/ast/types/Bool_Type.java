package ast.types;

public class Bool_Type extends Type {
    public Bool_Type(int row) {
        super(Type_T.BOOL, row);        
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