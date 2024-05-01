package ast.expressions.values;

import ast.ASTNode;
import ast.types.Type;

public class FieldID extends VariableID {
    public FieldID(String v, int row) {
        super(v, row);
    }

    public void setReference(ASTNode a) {
        this.id_node = a;
    }
   
    @Override
    public void bind() {
        
    }

    @Override
    public Type checkType() throws Exception {
        return null; // Nothing to do
    }  
}
