package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.values.Literal;
import ast.sentences.Block;
import ast.types.Type;

public class Case_Ins extends Instruction {

    public Case_Ins(Literal cond, Block body, int row) {
        super(cond, body, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("case: " + argExpression.toString() + '\n');
        str.append(body.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        argExpression.bind(); // argExpression should be a "constant" and the binding wont change anything
        body.bind();
    }

    @Override
    public Type checkType() throws Exception {
        try {
            Type t = argExpression.checkType();
            return t;
        } catch (Exception e) {
            System.out.println("Case expresion failed: " + e.getMessage());
        }
        body.checkType();
        return null;
    }

    @Override
    public void maxMemory(Integer c, Integer max) {
        body.maxMemory(c, max);
        // FIXME falta el literal 100 por 100 seguro
    }
}