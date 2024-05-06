package ast.expressions.operators;

import ast.expressions.UnaryExpression;
import ast.types.interfaces.Pointer_Type;
import ast.types.interfaces.Type;
import ast.expressions.Expression;
import exceptions.InvalidTypeException;


public class Reference_Op extends UnaryExpression {
    public Reference_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "&" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type t = opnd1().getType();
        if (t == null)
            throw new InvalidTypeException(String.format("Referenced expression must have type"));
        this.type = new Pointer_Type(t, row); // aquí no pasa nada por no bindear Pointer_Type porque su interno ya estaba bindeado
        this.type.checkType();
    }
}