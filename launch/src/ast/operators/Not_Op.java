package ast.operators;

import ast.EUnary;
import ast.Expression;
import ast.types.Bool_Type;
import ast.types.Type.Type_T;
import exceptions.UnexpectedTypeException;


public class Not_Op extends EUnary {
    public Not_Op(Expression opnd, int row) {
        super(opnd, row);
    }
    
    public String toString() {return "!" + opnd1().toString();};

    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public void checkType() throws Exception { 
        opnd1().checkType();
        if (opnd1().getType() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected but " + opnd1().getType().name() + " was read");
        this.type = new Bool_Type(row);    
    }
}
