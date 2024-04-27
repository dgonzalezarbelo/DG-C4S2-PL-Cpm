package ast.operators;

import java.util.List;

import ast.ASTNode;
import ast.Expression;
import ast.KindE;
import ast.Utils;
import ast.preamble.Program;
import ast.types.Id_Value;
import exceptions.InvalidIdException;

public class Parentesis_Op extends Expression { 
    private List<ASTNode> functionReferences;
    private Id_Value id;
    private List<Expression> args;

    public Parentesis_Op(String opnd1, List<Expression> opnd2, int row) {
        this.id = new Id_Value(opnd1, row);
        this.args = opnd2;
    }
    public KindE kind() {return KindE.SUMA;}
    public String toString() {return id.toString() + args.toString();}

    @Override
    public void bind() { //TODO errores de binding identificador
        try {
            this.functionReferences = Program.symbolsTable.getMethodsDefinitions(this.id.getValue());
        } catch (InvalidIdException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        for (Expression exp : args)
          exp.bind();
    }
}