package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;

public class Output_Ins extends Instruction {

    public Output_Ins(Expression expression, int row) {
        super(expression, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("printing " + argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public void bind() {
        this.argExpression.bind();
    }

    @Override
	public Type checkType() throws Exception {
        try {
            Type t = argExpression.checkType();
            if (t.getKind() != Type_T.INT && t.getKind() != Type_T.BOOL)
                throw new InvalidTypeException("I/O functions can only be used with int or bool variables");
            return t;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }    
}