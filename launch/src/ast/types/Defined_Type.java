package ast.types;

import ast.Utils;
import ast.preamble.Definition;
import ast.preamble.Program;
import exceptions.InvalidTypeException;

public class Defined_Type extends Type { //User defined type 
    private String name;
    private Definition type_definition; 

    public Defined_Type(String name, int row) {
        super(Type_T.TEMP_UNKNOWN, row);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {return name;}

    @Override
    public void bind() {
        try {
            this.type_definition = Program.symbolsTable.getDefinition(name);
        } catch (InvalidTypeException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    } 
}
