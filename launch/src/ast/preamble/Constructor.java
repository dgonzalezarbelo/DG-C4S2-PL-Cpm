package ast.preamble;

import java.util.List;

import ast.expressions.Expression;
import ast.sentences.Block;
import ast.types.Type;

public class Constructor extends Method {
    private Definition def;

    public Constructor(String name, List<Argument> args, Type return_t, Block body, Expression return_var, int row) {
        super(name, args, return_t, body, return_var, new Public_Vis(row), row);
    }

    public Type getType(){
        return def.getType();
    }

    @Override
    public Type checkType() throws Exception {
        body.checkType();
        if (this.def != null)
            return this.def.getRootType();
        return null;
    }

    @Override
    public void bind() {
        super.propagateBind();
        try {
            def = Program.symbolsTable.getDefinition(this.id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}