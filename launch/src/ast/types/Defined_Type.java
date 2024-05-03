package ast.types;

import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.preamble.Attribute;
import ast.preamble.Definition;
import ast.preamble.Method;
import ast.preamble.Program;
import exceptions.InvalidTypeException;

public class Defined_Type extends Type {    // User defined type 
    private String name;
    private Definition type_definition;     // Contains the definition of this defined type

    public Defined_Type(String name, int row) {
        super(Type_T.TEMP_UNKNOWN, row);
        this.name = name;
    }

    public Defined_Type(String name, int row, Type_T real_type_t, Definition type_definition) {
        super(real_type_t, row);
        this.name = name;
        this.type_definition = type_definition;
    }

    public String getName() {
        return this.type_definition.getName();
    }

    public String toString() {return name;}

    @Override
    public void bind() {
        try {
            this.type_definition = Program.symbolsTable.getDefinition(name);
        } catch (InvalidTypeException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }

    @Override
    public Type checkType() throws Exception { 
        try {
            if (this.kind == Type_T.TEMP_UNKNOWN)
                this.kind = type_definition.checkKind();
        }
        catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        return super.checkType();
    }

    @Override
    public boolean equals(Type other) {
        if (super.equals(other))
            return this.getName().equals(other.getName());
        else
            return false;
    }

    public Method hasMethod(MethodCall m) throws Exception {
        return this.type_definition.hasMethod(m);
    }

    public Attribute hasAttribute(FieldID name, boolean insideClass) throws Exception {
        return this.type_definition.hasAttribute(name, insideClass);
    }

    @Override
    public Type getRootType() {
        return this.type_definition.getRootType();
    }

    @Override
    public Type getType() {
        return getRootType();
    }
}
