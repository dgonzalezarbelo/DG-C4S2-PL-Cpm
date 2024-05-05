package ast.types;

import ast.ASTNode;

public abstract class Type implements ASTNode {
    protected int row;

    protected Type_T kind;
    public static enum Type_T { INT, BOOL, CLASS, STRUCT, POINTER, ARRAY, TEMP_UNKNOWN, CONST };
    protected Integer size;

    public Type(Type_T v, int row) {
        this.kind = v;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public Type_T getKind() {
        return this.kind;
    }

    public abstract String getName();

    public String toString() {
        return this.kind.name().toLowerCase();
    }

    @Override
    public Type checkType() throws Exception {
        return this;
    }

    @Override
    public Type getType() {
        return this;
    }

    @Override
    public void propagateIndentation(int indent) {
        // Nothing to do
    }

    public Type getRootType() {
        return this;
    }

    public boolean canBeAssigned(Type other) {
        return this.equals(other);
    }

    public boolean equals(Type other) {
        if (other == null)
            return false;
        else
            return this.getKind() == other.getKind();
    }

    public void calcSize() {
        size = 4;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public void maxMemory(Integer c, Integer max) {
        calcSize();
        max = size;
    }
} 
