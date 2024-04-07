package ast.preamble;

import java.util.List;

public class Program {
    private List<Definition> definitions;
    private Function mainFuncion;

    public Program(List<Definition> definitions, Function mainFunction) {
        this.definitions = definitions;
        this.mainFuncion = mainFunction;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("PROGRAM: \n");
        for (Definition i : definitions)
            str.append(i.toString() + "\n");
        str.append(mainFuncion.toString() + "\n");
        return str.toString();
    }     
}
