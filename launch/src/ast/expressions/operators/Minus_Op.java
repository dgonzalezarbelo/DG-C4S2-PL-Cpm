package ast.expressions.operators;

import ast.expressions.UnaryExpression;
import ast.types.interfaces.Int_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import ast.Josito;
import ast.expressions.Expression;
import exceptions.InvalidDirectionException;
import exceptions.UnexpectedTypeException;


public class Minus_Op extends UnaryExpression {
    public Minus_Op(Expression opnd, int row) {
        super(opnd, row);
        this.type = new Int_Type(row);
        this.operator = Operator_T.MINUS;
    }
    
    
    public String toString() {return "-" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
        this.type.bind();
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type t = opnd1().getType();
        if (t.getKind() != Type_T.INT)
            throw new UnexpectedTypeException(String.format("'%s' was expected but '%s' was read", Type_T.INT.name(), t.getKind().name()));
        this.type.checkType();
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        throw new InvalidDirectionException("Minus operation is not directionable");
    }
}
