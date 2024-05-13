package ast.expressions;

import ast.Josito;
import ast.SymbolsTable;
import utils.GoodBoolean;

public abstract class BinaryExpression extends Expression {
    private Expression opnd1;
    private Expression opnd2;
    
    public BinaryExpression(Expression opnd1, Expression opnd2, int row) {
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
        this.row = row;
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        if (opnd1 != null)
            opnd1.propagateStaticVars(g, s);
        if (opnd2 != null)
            opnd2.propagateStaticVars(g, s);
    }

    @Override
    public void checkType() throws Exception {
        this.opnd1.checkType();
        this.opnd2.checkType();
    }

    @Override
    public Expression opnd1() {return opnd1;}
    @Override
    public Expression opnd2() {return opnd2;}

    public void generateValue(Josito jose) throws Exception { // Code_E
        if (opnd1() != null)
            opnd1().generateValue(jose);
        if (opnd2() != null)
            opnd2().generateValue(jose);
        jose.translateOperator(this.operator);
    }
}
