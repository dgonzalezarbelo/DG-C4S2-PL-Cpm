package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;
import ast.types.Int_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.UnexpectedTypeException;


public class Minus_Op extends EUnary {
    public Minus_Op(Expression opnd, int row) {
        super(opnd, row);
        this.type = new Int_Type(row);
    }
    
    
    public String toString() {return "-" + opnd1().toString();}
   
    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public Type checkType() throws Exception { 
        Type t = opnd1().checkType();
        if (t.getKind() != Type_T.INT)
            throw new UnexpectedTypeException(Type_T.INT.name() + " was expected but " + t.getKind().name() + " was read at row " + this.row);
        return type;   
    }
}
