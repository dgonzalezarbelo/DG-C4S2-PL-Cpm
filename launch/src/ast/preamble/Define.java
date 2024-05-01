package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.Literal;
import exceptions.DuplicateDefinitionException;
import ast.types.Type;
import ast.types.Type.Type_T;

public class Define extends Definition {
    private Literal expression; 

    public Define(String id, Literal expression, int row) { //FIXME El id no deberia ser un Id_Type, sino Id_Value. El tipo es el de la expresion
        super(id, row);
        this.expression = expression;
    }
    
    @Override
    public String toString() {
        return "define " + id + " " + expression.toString();
    }

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }

	@Override
	public void bind() {
        try {
            // The order matters!
            Program.symbolsTable.insertSymbol(id, this);        // We also save it as a symbol, as it acts as a "global variable"
            Program.symbolsTable.insertDefinitions(id, this);   // We save the new definition (keyword)
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}
    
    @Override
    public Type checkType() throws Exception {
        // Nothing to do
        return expression.checkType();
    }

    
    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

	@Override
	public Type_T checkKind() {
		return null;
	}

	@Override
	public Attribute hasAttribute(String name) {
		return null;
	}

	@Override
	public Method hasMethod(MethodCall fc) {
		return null;
	}
}
