package ast.preamble;

import java.util.List;

import ast.Utils;
import ast.expressions.Expression;
import ast.sentences.Block;
import ast.types.interfaces.Type;
import exceptions.DuplicateDefinitionException;

public class Method extends Function { // Class method represents the class functions
    protected Visibility visibility;

    public Method(Function f, Visibility visibility) {
        super(f.getDefinitionName(), f.args, f.return_t, f.body, f.return_var, f.getRow());
        this.visibility = visibility;
    }
    
    // Adding visibility to the function
    public Method(String name, List<Argument> args, Type return_t, Block body, Expression return_var, Visibility visibility, int row) {
        super(name, args, return_t, body, return_var, row);
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        StringBuilder str = new StringBuilder();
        if (visibility != null) {
            for (int j = 0; j < indentation; j++)
                str.append(" ");
            str.append("Visibility: " + visibility.toString() + "\n");
        }
        Utils.appendIndent(str, indentation);
        str.append("Function: " + definitionName + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Tipo: " + return_t + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Cuerpo: \n" + body);
        Utils.appendIndent(str, indentation);
        str.append("Return: " + return_var + "\n");
        return str.toString();
    }
    
    @Override
    public void bind() {
        try {
            Program.symbolsTable.insertFunction(this.definitionName, this);
            propagateBind();
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }
}
