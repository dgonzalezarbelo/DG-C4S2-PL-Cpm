package ast.expressions.values;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import ast.types.Type;
import exceptions.InvalidIdException;

public class VariableID extends Expression {
    protected String v;
    protected ASTNode id_node; // Declaration or define node reference

    public VariableID(String v, int row) {
        this.v = v;
        this.row = row;  
    }
    public String getValue() {return v;}
    public String toString() {return v;}

    @Override
    public void bind() {
        try {
          this.id_node = Program.symbolsTable.getReference(v);
        } catch (InvalidIdException e) {
          System.out.println(e);
          Utils.printErrorRow(row);
        }
    }

    @Override
    public Type checkType() throws Exception {
        return this.id_node.getType();
    }  
}
