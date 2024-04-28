package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Expression;
import ast.Utils;
import exceptions.DuplicateDefinitionException;

public class Define extends Definition {
    private Expression expression; // TODO [dani, no lo quites] quiza podria ser un tipo Literal que sea una clase abstracta que englobe a los tipos basicos Integer, Bool 

    public Define(String id, Expression expression, int row) { //FIXME El id no deberia ser un Id_Type, sino Id_Value. El tipo es el de la expresion
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
    public void checkType() {
        // nothing to do
    }

    
    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }
}
