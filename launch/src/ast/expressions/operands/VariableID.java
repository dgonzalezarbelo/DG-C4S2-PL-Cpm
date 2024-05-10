package ast.expressions.operands;

import ast.ASTNodeTypable;
import ast.Josito;
import ast.expressions.Expression;
import ast.preamble.Attribute;
import ast.preamble.Program;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Const_Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidDirectionException;
import exceptions.InvalidIdException;
import utils.Utils;
import ast.types.definitions.Define;

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
	public void generateAddress(Josito jose) throws Exception { // Code_D
	//TODO Va a haber que hacer una distincion en caso de ser una referencia
		if (!this.type.getClass().equals(Const_Type.class)) {
			Declaration cast = (Declaration)id_node;
			Integer delta = cast.getOffset();
			if (cast instanceof Attribute)
				jose.getLocalDirUsingRef(delta);
			else
				jose.getLocalDirUsingMP(delta);
		}
		else {
			throw new InvalidDirectionException("Const values can not be directionable");
		}
	}
    
	@Override
    public void generateValue(Josito jose) throws Exception { // Code_E
	//TODO Va a haber que hacer una distincion en caso de ser una referencia
		generateAddress(jose);
		Type_T t = this.type.getKind();
		switch (t) {		// TODO igual esto puede ir en el tipo haciendo type.generateValue() y nos quitamos problemas de varios sitios
            case INT:
            case BOOL:
			case POINTER:
				jose.load();
				break;
			case ARRAY:
			case CLASS:
            case STRUCT:
				// In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
                break;
            case CONST: // This will only be a define
				jose.createConst(((Define)id_node).getLiteral().toIntConst());
                break;
            default:
                break;
        }
    }
}
