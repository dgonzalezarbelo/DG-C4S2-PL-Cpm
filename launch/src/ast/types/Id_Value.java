package ast.types;

import ast.ASTNode;
import ast.Expression;
import ast.KindE;
import ast.Utils;
import ast.preamble.Program;
import exceptions.InvalidIdException;

public class Id_Value extends Expression {
    private String v;
    private ASTNode id_node;

    public Id_Value(String v, int row) {
        this.v = v;
        this.row = row;  
    }
    public String getValue() {return v;}
    public KindE kind() {return KindE.NUM;}   
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
}
