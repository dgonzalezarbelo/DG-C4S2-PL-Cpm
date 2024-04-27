package ast.types;

import ast.Utils;
import ast.preamble.Definition;
import ast.preamble.Program;
import exceptions.InvalidTypeException;

public class Id_Type extends Type {
    private String name;
    private Definition type_definition;
    private Definition root_definition;

    public Id_Type(String name, int row) {
        super(Type_T.ID, row);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {return name;}

    @Override
    public void bind() { //TODO errores de binding tipado
        try {
            this.type_definition = Program.symbolsTable.getDefinition(name);
        } catch (InvalidTypeException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    } 
}
