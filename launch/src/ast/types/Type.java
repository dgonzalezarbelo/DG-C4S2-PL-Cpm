package ast.types;

import ast.ASTNode;

public abstract class Type implements ASTNode {
    protected int row;

    protected Type_T kind;
    public static enum Type_T { INT, BOOL, ARRAY, CLASS, STRUCT, POINTER, REFERENCE, TEMP_UNKNOWN };

    public Type(Type_T v, int row) {
        this.kind = v;
        this.row = row;
    }

    public Type_T getKind() {
        return this.kind;
    }

    public String toString() {
        return this.kind.name().toLowerCase();
    }

    @Override
    public void propagateIndentation(int indent) {
        // Nothing to do
    }
} 
