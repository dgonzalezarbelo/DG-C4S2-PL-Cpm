package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.types.Type.Type_T;
import ast.types.Type;

public abstract class Definition implements ASTNode {
    protected Integer indentation;
    protected int row;
    protected String id;
    
    public Definition(String name, int row) {
        this.indentation = null;
        this.id = name;
        this.row = row;
    }
    
    public String getId() {
        return this.id;
    }

    public int getRow() {
        return this.row;
    }

    public Type getRootType() {
        // Nothing to do in general
        return null;
    }

    public String getName() {
        return this.id;
    }

    public abstract Type_T checkKind() throws Exception; // this function returns the kind of the definition of the defined type 
    public abstract Attribute hasAttribute(FieldID name, boolean insideClass) throws Exception;
    public abstract Method hasMethod(MethodCall fc) throws Exception;
    public abstract String toString();
    public abstract List<ASTNode> getConstructors();
}