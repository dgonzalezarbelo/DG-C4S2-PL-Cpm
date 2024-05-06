package ast.sentences.instructions;

import ast.Utils;
import ast.expressions.Expression;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
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
    public void checkType() throws Exception { // TODO quiza revisar que solo sea una variable o un struct.variable para hacer cin correctamente
        try {
            super.checkType();
            Type condType = argExpression.getType();
            if (condType.getKind() == null || (condType.getKind() != Type_T.BOOL && condType.getKind() != Type_T.INT))
                throw new InvalidTypeException("I/O functions can only be used with int or bool variables");
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }
}