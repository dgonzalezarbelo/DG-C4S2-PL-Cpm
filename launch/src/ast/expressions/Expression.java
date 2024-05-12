package ast.expressions;

import ast.ASTNodeTypable;
import ast.Delta;
import ast.Indentable;
import ast.Josito;
import utils.GoodBoolean;
import utils.GoodInteger;

public abstract class Expression extends ASTNodeTypable implements Indentable {
    public static enum Operator_T { AND, DIV, EQ, FIELD_ACCESS, GREATER, GEQ, LESS, LEQ, MINUS, MOD, MULT, NEQ, NOT, OR, PTR, POW, REFERENCE, SQ_BRACKET, SUB, ADD }
    protected int indentation;
    protected Operator_T operator;
    public abstract Expression opnd1();
    public abstract Expression opnd2();

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
    }

    @Override
    public void maxMemory(GoodInteger c, GoodInteger maxi) { 
        maximumMemory.setValue(0);
    }

    @Override
    public void computeOffset(Delta delta) {
        // Nothing to do
    }

    @Override
    public void generateCode(Josito jose) {
        // Nothing to do
    }
    
    public abstract void generateAddress(Josito jose) throws Exception;// Code_D
    
    public void generateValue(Josito jose) throws Exception { // Code_E
        if (opnd1() != null)
            opnd1().generateValue(jose);
        if (opnd2() != null)
            opnd2().generateValue(jose);
        jose.translateOperator(this.operator);
    }
}
