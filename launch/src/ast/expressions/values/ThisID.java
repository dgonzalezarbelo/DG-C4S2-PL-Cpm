package ast.expressions.values;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import ast.types.Type;
import exceptions.InvalidTypeException;

public class ThisID extends Expression {
    private ASTNode thisReference; // This must be a class or a struct

    public ThisID(int row) {
        this.row = row;
    }
    
    public String toString() {return "dis." + opnd1().toString();}

    @Override
    public void bind() {
        String currDef = Program.symbolsTable.getCurrentDefinition();
        if(currDef == "") {
            System.out.println("InvalidThisException: using this operator outside a class or a struct");
            Utils.printErrorRow(row);
        }
        else {
            try {
                thisReference = Program.symbolsTable.getDefinition(currDef);
            } catch (InvalidTypeException e) { // This shouldnt catch any exception
                e.printStackTrace();
                System.out.println("If this catch is activated means that we are having porblems inserting classes in the SymbolsTable");
            }
        }
    }

    @Override
    public Type checkType() throws Exception {
        return this.thisReference.checkType();
    } 
}