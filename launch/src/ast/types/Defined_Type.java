package ast.types;

import ast.Utils;
import ast.preamble.Definition;
import ast.preamble.Program;
import exceptions.InvalidTypeException;

public class Defined_Type extends Type {    // User defined type 
    private String name;
    private Definition type_definition;     // Contains the definition of this defined type

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

    @Override
    public Type checkType() throws Exception { // TODO por si acaso falla [tener en cuenta que subimos hasta la clase/struct]
        this.kind = type_definition.checkType().getKind();
        return super.checkType();
    }

    @Override
    public boolean equals(Type other) {
        if (super.equals(other)) {
            Defined_Type dother = (Defined_Type) other;
            return this.getName().equals(dother.getName());
        }            
        else
            return false;
    }
}
