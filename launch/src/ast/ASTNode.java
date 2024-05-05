package ast;

import ast.types.Type;

public interface ASTNode {
    // public ?? generateCode() // for the future
    
    public void bind();

    public Type checkType() throws Exception;

    public String toString();

    public void propagateIndentation(int indent); // To print the AST

    public default Type getType() {
        return null;
    }

    public /*default*/ void maxMemory(Integer c, Integer max); // { /*Nothing to do*/ }
}