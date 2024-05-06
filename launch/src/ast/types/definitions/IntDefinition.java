package ast.types.definitions;

import java.util.List;

import ast.ASTNode;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.types.interfaces.Int_Type;

public class IntDefinition extends Definition {

    public IntDefinition() {
        super(Int_Type.NAME, 0);
    }

    @Override
    public List<ASTNode> getConstructors() {
        throw new UnsupportedOperationException("Unimplemented method 'getConstructors'");
    }

    @Override
    public String toString() {
        return this.definitionName;
    }
    
    @Override
    public void checkType() throws Exception {
        this.type = new Int_Type(row);
    }

    @Override
    public Attribute hasAttribute(AttributeID name, boolean insideClass) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasAttribute'");
    }

    @Override
    public Method hasMethod(FunctionCall mc) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasMethod'");
    }

    @Override
    public void maxMemory(Integer c, Integer maxi) {
        maximumMemory = 0;
    }
    
}
