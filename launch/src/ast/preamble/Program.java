package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.SymbolsTable;
import ast.types.Type;

public class Program implements ASTNode {
    private List<Definition> definitions;
    private Function mainFuncion;
    public static SymbolsTable symbolsTable = new SymbolsTable();

    public Program(List<Definition> definitions, Function mainFunction) {
        this.definitions = definitions;
        this.mainFuncion = mainFunction;
    }

    public String toString() {
        for(Definition d : definitions)
            d.propagateIndentation(0);
        mainFuncion.propagateIndentation(0);
        StringBuilder str = new StringBuilder();
        str.append("PROGRAM: \n");
        for (Definition d : definitions)
            str.append(d.toString() + "\n");
        str.append(mainFuncion.toString() + "\n");
        return str.toString();
    }

    @Override
    public void bind() {
        for (Definition d : definitions) {
            d.bind();
        }
        mainFuncion.bind();
    }

    @Override
    public Type checkType() {
        // Preprocess for the typedef and define statements
        for (Definition d : definitions)
            d.getRootType();

        try {
            for (Definition d : definitions)
                d.checkType();
            mainFuncion.checkType();
        } catch (Exception e) {
            System.out.println("This exception indicates that something far away from typing went wrong (in other case the program would have restored at a class/struct/function definition)");
        }
        
        return null;
    }  

    @Override
    public void propagateIndentation(int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propagateIndentation'");
    }

      
}
