package ast.types;

import ast.ASTNode;
import ast.Expression;
import ast.Utils;
import ast.preamble.Program;
import exceptions.InvalidIdException;

public class VariableID extends Expression {
    private String v;
    private ASTNode id_node;

    public VariableID(String v, int row) {
        this.v = v;
        this.row = row;  
    }
    public String getValue() {return v;}
    public String toString() {return v;}

    @Override
    public void bind() {  //TODO errores de binding identificador
        try {
          this.id_node = Program.symbolsTable.getReference(v);
        } catch (InvalidIdException e) {
          System.out.println(e);
          Utils.printErrorRow(row);
        }
    }
    @Override
    public void checkType() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'checkType'");
    }  
}
