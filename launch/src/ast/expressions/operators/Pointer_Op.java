package ast.expressions.operators;

import ast.expressions.UnaryExpression;
import ast.types.interfaces.Pointer_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import ast.Josito;
import ast.expressions.Expression;
import exceptions.InvalidTypeException;


public class Pointer_Op extends UnaryExpression {
    public Pointer_Op(Expression opnd, int row) {
        super(opnd, row);
        this.operator = Operator_T.PTR;
    }
    
    public String toString() {return opnd1().toString() + "~";}

    @Override
    public void bind() {
        opnd1().bind();
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        Type t = opnd1().getType();
        if (t.getKind() != Type_T.POINTER)
            throw new InvalidTypeException(String.format("'%s' was expected but '%s' was read", Type_T.POINTER.name(), t.getKind().name()));
        this.type = ((Pointer_Type) t).getInnerType();
        this.type.checkType();
    }

    @Override
    public void generateAddress(Josito jose) throws Exception { // Code_D
        opnd1().generateValue(jose);                // Requests pointed dir (! not pointer)
    }
    
    @Override
    public void generateValue(Josito jose) throws Exception {   // Code_E
        opnd1().generateValue(jose);                // Requests pointed dir (! not pointer)
        Type_T t = this.type.getKind();             // Loads the value stored in previous dir
		switch (t) {
            case INT:
            case BOOL:
			case POINTER:
				jose.load();
				break;
			case ARRAY:
			case CLASS:
            case STRUCT:
				// In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
                break;
            case CONST: // This will only be a define
                // This will never be the case
                break;
            default:
                break;
    
        }
    }
}

/**
     * int ~ a = 0;
     */

    /**
     * ~d
     * 
     * int a;
     * int~ d, b;
     * d = b;
     * 
     * int~~ c;
     * ~(~c) = a;
     * 
     * ~d = a;
     * a = ~d;
     * d = b;
     * ~d = ~b;
     * a = ~d
     */
