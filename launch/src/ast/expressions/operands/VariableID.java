package ast.expressions.operands;

import ast.ASTNodeTypable;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import exceptions.InvalidIdException;

public class VariableID extends Expression {
	protected String varname;
	protected ASTNodeTypable id_node; // Declaration or define node reference

	public VariableID(String v, int row) {
		this.varname = v;
		this.row = row;
	}

	public String getValue() {
		return varname;
	}

	public String toString() {
		return varname;
	}

	@Override
	public void bind() {
		try {
			this.id_node = Program.symbolsTable.getReference(varname);
		} catch (InvalidIdException e) {
			System.out.println(e);
			Utils.printErrorRow(row);
		}
	}

	@Override
	public void checkType() throws Exception {
		this.type = this.id_node.getType();
	}

	@Override
	public Expression opnd1() {
		throw new UnsupportedOperationException("Variables do not have operands");
	}

	@Override
	public Expression opnd2() {
		throw new UnsupportedOperationException("Variables do not have operands");
	}
}
