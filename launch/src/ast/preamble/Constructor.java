package ast.preamble;

import java.util.List;

import ast.expressions.Expression;
import ast.sentences.Block;
import ast.sentences.declarations.Declaration;
import ast.types.Type;

public class Constructor extends Method {
    public Constructor(String name, List<Declaration> args, Type return_t, Block body, Expression return_var, int row) {
        super(name, args, return_t, body, return_var, new Public_Vis(row), row);
    }


    @Override
    public void bind() {
        super.propagateBind();
    }
}