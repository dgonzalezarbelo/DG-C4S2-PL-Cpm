package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.types.Type;
import ast.types.Type.Type_T;

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

    public abstract Type_T checkKind();
    public abstract Attribute hasAttribute(FieldID name);
    public abstract Method hasMethod(MethodCall fc);
    public abstract String toString();
    public abstract List<ASTNode> getReferences();
}