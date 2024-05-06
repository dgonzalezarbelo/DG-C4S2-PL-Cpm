package ast.types.definitions;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.preamble.Program;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Type;

public class Typedef extends Definition {
    private Type existing_type;     // The previously existing type

    public Typedef(Declaration dec, int row) {
        super(dec.getVarname(), row); // We store the new alias in the id
        this.existing_type = dec.getType();
    }

    @Override
    public String toString() {
        return "typedef " + existing_type.toString() + definitionName.toString();
    }

    @Override
    public void bind() {
        existing_type.bind();
        try {
			Program.symbolsTable.copyDefinition(this.definitionName, existing_type.getTypename());
		} catch (Exception e) {
			System.out.println(e);
            Utils.printErrorRow(row);
		}
    }

    @Override
    public void checkType() throws Exception {
        // Nothing to do because typedef is already resolved
    }

    @Override
    public List<ASTNode> getConstructors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConstructors'");
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
}

//FIXME
/*
taipdef int i
taipdef i entero
 */