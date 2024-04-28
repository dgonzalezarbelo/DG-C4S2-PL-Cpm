package ast.operators;

import ast.ASTNode;
import ast.EUnary;
import ast.Utils;
import ast.preamble.Program;
import ast.types.VariableID;
import exceptions.InvalidTypeException;

public class This_Op extends EUnary {
    ASTNode thisReference;

    public This_Op(String opnd, int row) {
        super(new VariableID(opnd, row), row);
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
    public void checkType() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkType'");
    }
}


/*
clas Alumno {
    public

int a;
this.a;
}
*/