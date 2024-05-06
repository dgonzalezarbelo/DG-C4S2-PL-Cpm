package ast.expressions.operators;

import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.types.interfaces.Int_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.MatchingTypeException;
import exceptions.UnexpectedTypeException;


public class Mod_Op extends BinaryExpression {
    public Mod_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);  
        this.type = new Int_Type(row);
    }
    
    public String toString() {return opnd1().toString() + " % " + opnd2().toString();}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
        this.type.bind();
    }
    
    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type left = opnd1().getType();
        Type right = opnd2().getType();
        if (left.getKind() != right.getKind())
            throw new MatchingTypeException(String.format("'%' operands '%s' and '%s' do not have the same type", opnd1().toString(), opnd2().toString()));
        if (left.getKind() != Type_T.INT)
            throw new UnexpectedTypeException(String.format("'%s' was expected but '%s' was read", Type_T.INT.name(), left.getKind().name()));
        this.type.checkType();
    }
}
