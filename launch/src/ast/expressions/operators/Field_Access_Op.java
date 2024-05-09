package ast.expressions.operators;

import ast.Josito;
import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.Field;
import ast.expressions.operands.MethodCall;
import ast.expressions.operands.ThisID;
import ast.types.interfaces.Defined_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidTypeException;

public class Field_Access_Op extends BinaryExpression {
    public Field_Access_Op(Expression opnd1, Field opnd2, int row) {
        super(opnd1, opnd2, row);
        this.operator = Operator_T.FIELD_ACCESS;
    }
    
    public String toString() {return opnd1().toString() + "." + opnd2().toString(); };

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public void checkType() throws Exception {
        opnd1().checkType();
        Type leftSide = opnd1().getType();
        if (leftSide.getKind() != Type_T.CLASS && leftSide.getKind() != Type_T.STRUCT)
            throw new InvalidTypeException("Field access operator '.' only applicable to classes or structs");
        boolean isThis = opnd1().getClass().equals(ThisID.class);
        ((Field) opnd2()).setClassFrom(((Defined_Type) opnd1().getType()), isThis);
        opnd2().checkType();
        this.type = opnd2().getType(); 
    }

    public void generateAddress(Josito jose) throws Exception {  // Code_D(d.id)
        opnd1().generateAddress(jose);          // Code_D(d)        // Stack object instance reference
        opnd2().generateAddress(jose);          // Code_D(id)
        if (opnd1() instanceof AttributeID) 
            jose.translateOperator(this.operator);  // This internally is an i32.add
    } // &(alumno.func)
    
    @Override
    public void generateValue(Josito jose) throws Exception {
        if (opnd1() instanceof MethodCall) {    // If method
            opnd1().generateAddress(jose);      // Stack in the stack the Code_D(d)
            opnd2().generateValue(jose);
        }
        else { // If attribute
            generateAddress(jose);              // Code_D(d.id)
            opnd2().generateValue(jose);
        }
    }
}

/**
 * 
 * struct Cosa {
 *      int param1;
 *      int param2;
 * }
 * 
 * class Alumno {
 *      int a;
 *      Cosa b;
 * }
 * ...
 * 
 *  Alumno a = Alumno();
 *  Cosa c = a.b;
 * 
 */