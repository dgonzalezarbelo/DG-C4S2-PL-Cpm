package ast.preamble;

import java.util.List;

import ast.expressions.Expression;
import ast.sentences.Block;
import ast.types.definitions.Definition;
import ast.types.interfaces.Type;

public class Constructor extends Method {
    private Definition def;

    public Constructor(String name, List<Argument> args, Type return_t, Block body, Expression return_var, int row) {
        super(name, args, return_t, body, return_var, new Public_Vis(), row);
    }

    public Type getType(){
        return def.getType();
    }

    @Override
    public void checkType() throws Exception {
        for (Argument a : args)
            a.checkType();
        body.checkType();
        this.return_t = def.getType();
        this.type = return_t;
    }

    @Override
    public void bind() {
        super.propagateBind();
        try {
            def = Program.symbolsTable.getDefinition(this.definitionName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}