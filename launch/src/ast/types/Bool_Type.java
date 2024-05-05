package ast.types;

import ast.preamble.Attribute;

public class Bool_Type extends Type {
    private static final String NAME = "bul";
    
    public Bool_Type(int row) {
        super(Type_T.BOOL, row);        
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