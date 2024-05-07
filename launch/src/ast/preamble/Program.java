package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Josito;
import ast.SymbolsTable;
import ast.types.definitions.BoolDefinition;
import ast.types.definitions.Definition;
import ast.types.definitions.IntDefinition;

public class Program extends ASTNode {
    private List<Definition> definitions;
    private Function mainFunction;
    public static SymbolsTable symbolsTable = new SymbolsTable();

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
        /*
         * Esto parece más feo de lo que es porque habría que hacerlo con un for y un "conjunto de definiciones de tipos básicos", pero hay que hacerlo
         * para estandarizar los tipos y no permitir declaraciones con el mismo nombre que otras (aunque sean de tipos basicos)
         */
        definitions.addFirst(new BoolDefinition());
        definitions.addFirst(new IntDefinition());
        for (Definition d : definitions)
            d.bind();
        mainFunction.bind();
    }

    @Override
    public void checkType() {
        try {
            for (Definition d : definitions)
                d.checkType();
            mainFunction.checkType();
        } catch (Exception e) {
            System.err.println("This exception indicates that something far away from typing went wrong (in other case the program would have restored at a class/struct/function definition or inside main function)");
            e.printStackTrace();
        }
    }

    @Override
    public void maxMemory(Integer c, Integer max) {
        for (Definition d : definitions)
            d.maxMemory(null, null);
        mainFunction.maxMemory(null, maximumMemory);
    }

    @Override
    public void computeOffset(Delta delta) {
        for (Definition d : definitions)
            d.computeOffset(delta);
        mainFunction.computeOffset(delta);
    }

    @Override
    public void generateCode(Josito jose) {
        jose.programHeader();
        for (Definition d : definitions)
            d.generateCode(jose);
        mainFunction.generateCode(jose);
    }
}
