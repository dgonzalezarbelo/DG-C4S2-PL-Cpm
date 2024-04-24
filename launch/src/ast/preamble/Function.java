package ast.preamble;

import java.util.List;

import ast.Expression;
import ast.Utils;
import ast.sentences.Block;
import ast.sentences.declarations.Declaration;
import ast.types.Type;

public class Function extends Definition {
    private List<Declaration> args;
    private Block body;
    private Type return_t;
    private Expression return_var;
    private Visibility visibility;

    public Function(String name, List<Declaration> args, Type return_t, Block body, Expression return_var) {
        super(name);
        this.args = args;
        this.return_t = return_t;
        this.body = body;
        this.return_var = return_var;
        this.visibility = null;
    }
    
    // Adding visibility to the function
    public Function(String name, List<Declaration> args, Type return_t, Block body, Expression return_var, Visibility visibility) {
        this(name, args, return_t, body, return_var);
        this.visibility = visibility;
    }

    public void setVisibility(Visibility vis) {
        this.visibility = vis;
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
        str.append("Function: " + id + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Tipo: " + return_t + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Cuerpo: \n" + body);
        Utils.appendIndent(str, indentation);
        str.append("Return: " + return_var + "\n");
        return str.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        this.body.propagateIndentation(indent + 1);
    }        
}
