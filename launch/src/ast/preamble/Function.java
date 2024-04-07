package ast.preamble;

import java.util.List;

import ast.sentences.Block;
import ast.sentences.declarations.DeclarationAndAssignation;

public class Function extends Definition {
    private List<DeclarationAndAssignation> args;
    private Block body;

    public Function(String name, Block body) {
        super(name);
        this.body = body;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Function: " + name + "\n");
        str.append("Cuerpo: " + body + "\n");
        return str.toString();
    }        
}
