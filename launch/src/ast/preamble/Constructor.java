package ast.preamble;

import java.util.List;

import ast.Expression;
import ast.sentences.Block;
import ast.sentences.declarations.Declaration;
import ast.types.Type;

public class Constructor extends Function {
    public Constructor(String name, List<Declaration> args, Type return_t, Block body, Expression return_var) {
        super(name, args, return_t, body, return_var);
    }
    
    // Adding visibility to the function
    public Constructor(String name, List<Declaration> args, Type return_t, Block body, Expression return_var, Visibility visibility) {
        super(name, args, return_t, body, return_var, visibility);
    }

    @Override
    public void bind() {
        // No se crea el nodo aqui
    }
}