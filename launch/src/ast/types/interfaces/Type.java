package ast.types.interfaces;

import ast.ASTNodeTypable;
import ast.Delta;
import ast.Utils;
import ast.preamble.Program;
import ast.types.definitions.Definition;
import exceptions.InvalidTypeException;

public abstract class Type extends ASTNodeTypable {
    public static enum Type_T { INT, BOOL, CLASS, STRUCT, POINTER, ARRAY, TEMP_UNKNOWN, CONST }
    protected String typename;
    protected Definition type_definition;
    protected Type_T kind;

    public Type(Type_T v, int row) {
        this.kind = v;
        this.row = row;
    }

    public Type_T getKind() {
        return this.kind;
    }

    public String getTypename() {
        return this.typename;
    }

    public String toString() {
        return this.kind.name().toLowerCase();
    }

    @Override
    public void bind() {
        try {
            this.type_definition = Program.symbolsTable.getDefinition(typename);
        } catch (InvalidTypeException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }

    @Override
    public void checkType() throws Exception {
        copyType(this.type_definition.getType());
    }

    public abstract void copyType(Type type) throws Exception;

    @Override
    public Type getType() {
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

    public abstract void calcSize();

    public Integer getSize() { 
        return maximumMemory;
    }

    @Override
    public void maxMemory(Integer c, Integer max) { 
        calcSize();
    }

    @Override
    public void computeOffset(Delta delta) {
        // Nothing to do
    }
}