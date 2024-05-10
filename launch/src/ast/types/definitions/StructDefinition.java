package ast.types.definitions;

import java.util.ArrayList;
import java.util.List;

import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Constructor;
import ast.preamble.Method;
import ast.types.interfaces.Defined_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import utils.Utils;

public class StructDefinition extends ObjectDefinition {
    protected List<Attribute> attributes;
    protected ClassFunctions functions;
    protected Type definedType;
    
    public StructDefinition(String name, List<Attribute> attributes, int row) {
        super(name, attributes, row);
    }
    
    public StructDefinition(String name, List<Attribute> attributes, List<Constructor> constructors, int row) {
        this(name, attributes, row);
        this.functions = new ClassFunctions(constructors, new ArrayList<>());
        this.definedType = new Defined_Type(definitionName, row, Type_T.STRUCT, this);
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        else
            this.propagateIndentation(this.indentation);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Struct: " + definitionName + "\n");
        str.append(super.toString());
        return str.toString();
    }

    @Override
    public void checkType() throws Exception {
        this.type = new Defined_Type(definitionName, row, Type_T.STRUCT, this);
        super.checkType();
    }

    @Override
    public Method hasMethod(FunctionCall mc) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'hasMethod'");
    }
}