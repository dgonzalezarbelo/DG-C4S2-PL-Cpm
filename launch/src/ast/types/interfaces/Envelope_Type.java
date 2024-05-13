package ast.types.interfaces;

import ast.SymbolsTable;
import exceptions.InvalidTypeException;
import utils.GoodBoolean;

public abstract class Envelope_Type extends Type {
    protected Type inner_type;

    public Envelope_Type(Type type, int row) {
        super(Type_T.POINTER, row); 
        this.inner_type = type;     
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        inner_type.propagateStaticVars(g, s);
    }

    public Type getInnerType() {
        return this.inner_type;
    }

    @Override
    public void bind() {
        this.inner_type.bind();
    }

    @Override
    public void checkType() throws Exception {
        this.inner_type.checkType();
    }

    @Override
    public void copyType(Type type) throws Exception {
        if (type.getKind() != this.getKind() || ((Envelope_Type) type).inner_type.getKind() != this.inner_type.getKind())
            throw new InvalidTypeException(String.format("Definition type '%s' doesn't match with '%s' declared type", type.toString(), this.toString()));
        this.inner_type.copyType(((Envelope_Type) type).inner_type);
    }

    @Override
    public boolean equals(Type other) {
        if(super.equals(other))
            return this.getInnerType().equals(((Envelope_Type) other).getInnerType());
        return false;
    }
}
