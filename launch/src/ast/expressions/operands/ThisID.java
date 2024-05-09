package ast.expressions.operands;

import ast.ASTNodeTypable;
import ast.Josito;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import exceptions.InvalidDirectionException;
import exceptions.InvalidTypeException;

public class ThisID extends Expression {
    private ASTNodeTypable thisReference; // This must be a class or a struct

    public ThisID(int row) {
        this.row = row;
    }
    
    public String toString() {return "dis";}

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
                System.out.println("If this catch is activated means that we are having porblems inserting classes in the SymbolsTable");
            }
        }
    }

    @Override
    public void checkType() throws Exception {
        this.type = this.thisReference.getType();
    }

    @Override
    public Expression opnd1() {
        throw new UnsupportedOperationException("'This' operator does not have operands");
    }

    @Override
    public Expression opnd2() {
        throw new UnsupportedOperationException("'This' operator does not have operands");
    }

    @Override
	public void generateAddress(Josito jose) throws Exception { // Code_D
		jose.getLocalDirUsingRef(0); // When using "this" you will be using it with a Field_Access_Op (this.), hence why you do not need to add the address of the instance of the Class/Struct you are in, because we are computing the position with respect to it already. That is why, we add 0
	}

    @Override
    public void generateValue(Josito jose) throws Exception {
        throw new InvalidDirectionException("This must not be directionable in a class definition");
    }
    
}