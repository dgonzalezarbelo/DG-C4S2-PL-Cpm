package ast.expressions.operands;

import ast.ASTNodeTypable;
import ast.Josito;
import ast.expressions.Expression;
import ast.preamble.Argument;
import ast.preamble.Attribute;
import ast.sentences.declarations.Declaration;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Const_Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidDirectionException;
import exceptions.InvalidIdException;
import exceptions.InvalidTypeException;
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
			this.id_node = symbolsTable.getReference(varname);
		} catch (InvalidIdException e) {
			System.out.println(e);
			Utils.printErrorRow(row);
		}
	}

	@Override
	public void checkType() throws Exception {
		this.type = this.id_node.getType();
		if (this.type.getKind() == Type_T.ARRAY) {
			Array_Type cast = (Array_Type)this.type;
			if (cast.isDynamic() && !(this.id_node instanceof Argument))
				throw new InvalidTypeException("Dynamic arrays are only allowed as arguments of functions");
		}
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
		if (!this.type.getClass().equals(Const_Type.class)) {
			Declaration cast = (Declaration)id_node;
			Integer delta = cast.getOffset();
			if (cast instanceof Argument) {
				jose.getLocalDirUsingMP(delta);
				Argument cast2 = (Argument)cast;
				if (cast2.isReference()) {
					jose.load();
				}
			}
			else {
				if (cast instanceof Attribute)
					jose.getLocalDirUsingRef(delta);
				else
					jose.getLocalDirUsingMP(delta);
			}
		}
		else {
			throw new InvalidDirectionException("Const values can not be directionable");
		}
	}
    
	@Override
    public void generateValue(Josito jose) throws Exception { // Code_E
		Type_T t = this.type.getKind();
		if (!this.type.getClass().equals(Const_Type.class)) {
			switch (t) {		// TODO igual esto puede ir en el tipo haciendo type.generateValue() y nos quitamos problemas de varios sitios
				case INT:
				case BOOL:
				case POINTER:
					generateAddress(jose);
					jose.load();
					break;
				case ARRAY:
					generateAddress(jose);
					Array_Type cast = (Array_Type)this.type;
					if (cast.isDynamic()) // We have to load the position of the start of the array in case it is dynamic
						jose.loadDynamicArray();
					break;
				case CLASS:
				case STRUCT:
					generateAddress(jose);
					// In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
					break;
				case CONST: // This will only be a define
					break;
				default:
					break;
			}
		}
		else {
			jose.createConst(((Define)id_node).getLiteral().toIntConst());
		}
    }
}
