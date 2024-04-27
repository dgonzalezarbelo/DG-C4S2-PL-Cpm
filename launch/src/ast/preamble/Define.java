package ast.preamble;

import ast.Expression;
import exceptions.DuplicateDefinitionException;

public class Define extends Definition {
    
    private Expression expression;

    public Define(String id, Expression expression) {
        super(id);
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
            Program.symbolsTable.insertSymbol(id.getName(), id);
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
        }
	}        
}
