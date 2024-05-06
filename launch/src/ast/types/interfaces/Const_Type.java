package ast.types.interfaces;

import ast.expressions.operands.Literal;
import exceptions.InvalidTypeException;

public class Const_Type extends Envelope_Type {    // User defined type 
    protected Literal value;
    
    public Const_Type(Type type, int row, Literal value) {
        super(type, row);
        this.kind = Type_T.CONST;
        this.value = value;
    }

    public String getTypename() {
        return this.inner_type.getTypename();
    }

    public Literal getConstValue() {
        return this.value;
    }

    @Override
    public Type_T getKind() {
        return this.inner_type.getKind();
    }

    public String toString() {return this.inner_type.toString();}

    @Override
    public boolean equals(Type other) {
        return this.inner_type.equals(other);
    }

    @Override
    public void copyType(Type type) throws Exception {
        if (type.kind != this.kind || type.getKind() != this.getKind())
            throw new InvalidTypeException(String.format("Definition type '%s' doesn't match with '%s' declared type", type.toString(), this.toString()));
        this.typename = type.typename;
        this.inner_type = ((Const_Type) type).inner_type;
        this.value = ((Const_Type) type).value;
    }

    @Override
    public void calcSize() { 
        this.inner_type.getType().calcSize();
        maximumMemory = this.inner_type.getSize();
    }
}
