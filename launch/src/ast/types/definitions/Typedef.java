package ast.types.definitions;
import java.util.List;

import ast.ASTNode;
import ast.SymbolsTable;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Type;
import utils.GoodBoolean;
import utils.GoodInteger;
import utils.Utils;

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
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        existing_type.propagateStaticVars(g, s);
    }

    @Override
    public void bind() {
        existing_type.bind();
        try {
			symbolsTable.copyDefinition(this.definitionName, existing_type.getTypename());
		} catch (Exception e) {
			System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
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

    @Override
    public void maxMemory(GoodInteger c, GoodInteger max) {
        maximumMemory.setValue(0);
    }
}

//FIXME
/*
taipdef int i
taipdef i entero
 */