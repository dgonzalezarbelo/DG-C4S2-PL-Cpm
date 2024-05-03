package ast.types;

import ast.expressions.values.Literal;

public class Const_Type extends Envelope_Type {    // User defined type 
    protected Literal value;
    
    public Const_Type(Type type, int row, Literal value) {
        super(type, row);
        this.kind = Type_T.CONST;
        this.value = value;
    }

    public String getName() {
        return this.inner_type.getName();
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
    public void bind() {
        // Nothing to do
    }

    @Override
    public boolean equals(Type other) {
        return this.inner_type.equals(other);
    }

    @Override
    public Type getRootType() {
        return this.inner_type.getRootType();
    }

    @Override
    public Type getType() {
        return getRootType();
    }

}
