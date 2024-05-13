package ast;

import ast.types.interfaces.Type;
import utils.GoodBoolean;

public abstract class ASTNodeTypable extends ASTNode {
    protected Type type;
    
    public Type getType() {
        return this.type;
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        if (type != null)
            type.propagateStaticVars(g, s);
    }
}