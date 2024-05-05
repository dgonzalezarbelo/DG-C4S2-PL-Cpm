package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.SymbolsTable;
import ast.types.Type;

public class Program implements ASTNode {
    private List<Definition> definitions;
    private Function mainFunction;
    public static SymbolsTable symbolsTable = new SymbolsTable();
    private Integer maximumMemory;

    public Program(List<Definition> definitions, Function mainFunction) {
        this.definitions = definitions;
        this.mainFunction = mainFunction;
    }

    public String toString() {
        for(Definition d : definitions)
            d.propagateIndentation(0);
        mainFunction.propagateIndentation(0);
        StringBuilder str = new StringBuilder();
        str.append("PROGRAM: \n");
        for (Definition d : definitions)
            str.append(d.toString() + "\n");
        str.append(mainFunction.toString() + "\n");
        return str.toString();
    }

    @Override
    public void bind() {
        for (Definition d : definitions) {
            d.bind();
        }
        mainFunction.bind();
    }

    @Override
    public Type checkType() {
        // Preprocess for the typedef and define statements
        for (Definition d : definitions)
            d.getRootType();

        try {
            for (Definition d : definitions)
                d.checkType();
            mainFunction.checkType();
        } catch (Exception e) {
            System.out.println("This exception indicates that something far away from typing went wrong (in other case the program would have restored at a class/struct/function definition)");
        }
        
        return null;
    }

    @Override
    public void maxMemory(Integer c, Integer max) {
        for (Definition d : definitions)
            d.maxMemory(0, 0);
        mainFunction.maxMemory(0, maximumMemory);
    }

    @Override
    public void propagateIndentation(int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propagateIndentation'");
    }

      
}
