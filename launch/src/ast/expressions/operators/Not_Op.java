package ast.expressions.operators;

import ast.expressions.UnaryExpression;
import ast.types.interfaces.Bool_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import ast.expressions.Expression;
import exceptions.UnexpectedTypeException;


public class Not_Op extends UnaryExpression {
    public Not_Op(Expression opnd, int row) {
        super(opnd, row);
        this.type = new Bool_Type(row);
        this.operator = Operator_T.NOT;
    }
    
    public String toString() {return "!" + opnd1().toString();};

    @Override
    public void bind() {
        opnd1().bind();
        this.type.bind();
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type t = opnd1().getType();
        if (t.getKind() != Type_T.BOOL)
            throw new UnexpectedTypeException(String.format("'%s' was expected but '%s' was read", Type_T.BOOL.name(), t.getKind().name()));
        this.type.checkType(); 
    }
}
