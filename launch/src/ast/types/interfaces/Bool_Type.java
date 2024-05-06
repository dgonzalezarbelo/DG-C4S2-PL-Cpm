package ast.types.interfaces;

import exceptions.InvalidTypeException;

public class Bool_Type extends Type {
    public static final String NAME = "bul";
    public static final int BYTES_SIZE = 4;
    
    public Bool_Type(int row) {
        super(Type_T.BOOL, row);
        this.typename = NAME;        
    }

    @Override
    public void copyType(Type type) throws Exception {
        if (type.kind != this.kind)
            throw new InvalidTypeException(String.format("Definition type '%s' doesn't match with '%s' declared type", type.toString(), this.toString()));
    }

    @Override
    public void calcSize() { 
        maximumMemory = BYTES_SIZE;
    }
}