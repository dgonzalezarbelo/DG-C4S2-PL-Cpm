package ast.types.interfaces;

import exceptions.InvalidTypeException;

public class Int_Type extends Type {
    public static final String NAME = "int";
    private static final int BYTES_SIZE = 4;

    public Int_Type(int row) {
        super(Type_T.INT, row);
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
