package ast.expressions.operators;

import ast.expressions.EUnary;
import ast.expressions.Expression;
import ast.types.Bool_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.UnexpectedTypeException;


public class Not_Op extends EUnary {
    public Not_Op(Expression opnd, int row) {
        super(opnd, row);
        this.type = new Bool_Type(row); 
    }
    
    public String toString() {return "!" + opnd1().toString();};

    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public Type checkType() throws Exception { 
        Type t = opnd1().checkType();
        if (t.getKind() != Type_T.BOOL)
            throw new UnexpectedTypeException(Type_T.BOOL.name() + " was expected but " + t.getKind().name() + " was read at row " + this.row);
        return type;   
    }
}
