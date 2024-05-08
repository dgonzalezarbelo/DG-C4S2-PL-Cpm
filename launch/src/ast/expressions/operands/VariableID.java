package ast.expressions.operands;

import ast.ASTNodeTypable;
import ast.Josito;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Const_Type;
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

	@Override
	public void generateAddress(Josito jose) { // Code_D
		if (!this.type.getClass().equals(Const_Type.class)) {
			Declaration cast = (Declaration)id_node;
			Integer delta = cast.getOffset();
			jose.createConst(delta);
			jose.load();
		}
		// TODO esto tiene que ser asi y que el delta sea el de la declaracion del id_node 
		// y no puede ser un define porque no se haría generateAddress a defines
		// jose.createIdentifier(delta); // si el id_node es un declaracion
	
	}
    
	@Override
    public void generateValue(Josito jose) { // Code_E
		// TODO esto hay que darle una vuelta para que se haga encapsulado

		// si el id_node es un declaracion
		// generateAdress(jose);
		// jose.load()

		// si el id_node es un define en lugar de una declaracion
		// jose.createConst(value) 	
	
    }
}
