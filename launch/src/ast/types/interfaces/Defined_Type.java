package ast.types.interfaces;

import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.types.definitions.Definition;

public class Defined_Type extends Type {    // User defined type 
    
    public Defined_Type(String name, int row) {
        super(Type_T.TEMP_UNKNOWN, row);
        this.typename = name;
    }

    public Defined_Type(String name, int row, Type_T real_type_t, Definition type_definition) {
        super(real_type_t, row);
        this.typename = name;
        this.type_definition = type_definition;
    }

    public String toString() {return typename;}

    @Override
    public void copyType(Type type) throws Exception {
        this.kind = type.kind;
        this.typename = type.typename;
    }

    @Override
    public boolean equals(Type other) {
        if (super.equals(other))
            return this.getTypename().equals(other.getTypename());
        else
            return false;
    }

    public Method hasMethod(FunctionCall m) throws Exception {
        return this.type_definition.hasMethod(m);
    }

    public Attribute hasAttribute(AttributeID name, boolean isThis) throws Exception {
        return this.type_definition.hasAttribute(name, isThis);
    }
}
