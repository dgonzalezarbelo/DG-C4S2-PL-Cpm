package ast.sentences.instructions;

import ast.Josito;
import ast.expressions.Expression;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidTypeException;
import utils.Utils;

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
    public void checkType() throws Exception {
        try {
            super.checkType();
            Type condType = argExpression.getType();
            if (condType.getKind() == null || (condType.getKind() != Type_T.BOOL && condType.getKind() != Type_T.INT))
                throw new InvalidTypeException("I/O functions can only be used with int or bool variables");
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }
    }
    
    @Override
    public void generateCode(Josito jose) { 
        try {
            argExpression.generateAddress(jose);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.printErrorRow(row);
        }
        jose.readCall();
        jose.store(); 
    }
}