package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Expression;
import ast.Utils;
import exceptions.DuplicateDefinitionException;

public class Define extends Definition {
    
    private Expression expression;

    public Define(String id, Expression expression, int row) {
        super(id, row);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "define " + id + " " + expression.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

	@Override
	public void bind() {
        try {
            // The order matters!
            Program.symbolsTable.insertSymbol(id.getName(), this); // We also save it as a symbol, as it acts as a "global variable"
            Program.symbolsTable.insertDefinitions(id.getName(), this); // We save the new definition (keyword)
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}

    @Override
    public List<ASTNode> getReferences() {
        List<ASTNode> list = new ArrayList<>();
        list.add(this);
        return list;
    }        
}
