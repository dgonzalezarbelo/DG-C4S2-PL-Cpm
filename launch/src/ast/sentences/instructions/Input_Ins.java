package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;

public class Input_Ins extends Instruction {

    public Input_Ins(Expression expression, int row) {
        super(expression, null, row);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("writing in " + this.argExpression.toString() + '\n');
        return str.toString();
    }

    @Override
    public void bind() {
        this.argExpression.bind();
    }

    @Override
    public Type checkType() throws Exception { // TODO quiza revisar que solo sea una variable o un struct.variable para hacer cin correctamente
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