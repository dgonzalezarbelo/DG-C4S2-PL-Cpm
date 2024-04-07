package ast.preamble;

import java.util.List;

import ast.Expression;
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
        StringBuilder str = new StringBuilder();
        if (visibility != null) str.append("Visibility: " + visibility.toString() + "\n");
        str.append("Function: " + id + "\n");
        str.append("Tipo: " + return_t + "\n");
        str.append("Cuerpo: " + body + "\n");
        str.append("Return: " + return_var + "\n");
        return str.toString();
    }        
}
