package ast.sentences.declarations;

import ast.Utils;
import ast.expressions.values.VariableID;
import ast.preamble.Program;
import ast.sentences.Sentence;
import ast.types.Array_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.DuplicateDefinitionException;

public class Declaration extends Sentence {
    protected Type type;
    protected VariableID id;

    public Declaration(Type type, String id, int row) {
        this.type = type;
        this.id = new VariableID(id, row);
        this.row = row;
    } 

    public Declaration(Declaration d) {
        this.type = d.type;
        this.id = d.id;
        this.row = d.row;
    }

    public VariableID getId() {
        return this.id;
    }

    public Type getType() {
        return this.type;
    }

    public static Declaration manageDeclaration(Type t, String id, Array_Type array, int row) {
        return new Declaration(manageType(t, array), id, row);
    }

    public static Type manageType(Type t, Array_Type array) {
        if (array != null) {
            array.setInnerType(t);
            return array.recoverBiggestArray(t, 1);
        }
        else
            return t;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(this.type.toString()
        + " " + id.toString() + '\n');
        return str.toString();
    }

    @Override
	public void bind() {
        type.bind();
        try {
            Program.symbolsTable.insertSymbol(id.getValue(), this);
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}

    @Override
    public Type checkType() throws Exception {
        if (this.type != null && type.getKind() == Type_T.ARRAY) {
            Array_Type array_type = (Array_Type) type;
            if (array_type.getDim() == null) {
                System.out.println("NoDimensionalArrayException: you can't declare the array " + id.toString()  + " with no dimension");
                Utils.printErrorRow(row);
                this.type = null;
                return null;
            }
        }
        return this.type;
    }  
}


