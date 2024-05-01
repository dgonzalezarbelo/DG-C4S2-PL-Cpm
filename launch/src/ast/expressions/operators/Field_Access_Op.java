package ast.expressions.operators;

import javax.naming.InvalidNameException;

import ast.expressions.EBin;
import ast.expressions.Expression;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.types.Defined_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;
import ast.expressions.values.FieldID;

public class Field_Access_Op extends EBin {
    public Field_Access_Op(Expression opnd1, Expression opnd2, int row) { 
        super(opnd1, opnd2, row);
    }
    
    public String toString() {return opnd1().toString() + "." + opnd2().toString(); };

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type leftSide = opnd1().checkType();
        Type rightSide = opnd2().checkType();
        if (leftSide.getKind() != Type_T.CLASS && leftSide.getKind() != Type_T.STRUCT)
            throw new InvalidTypeException("Field access operator '.' only applicable to classes or structs");
        
        if (opnd2().getClass().equals("Metodo")) { // TODO esto está mal hecho es para apañarme conceptualmente
            Method matched = ((Defined_Type) leftSide).hasMethod((MethodCall) opnd2()); 
            ((MethodCall) opnd2()).matchedFunction = matched; 
        }
        else if (opnd2().getClass().equals("campo")) {  // TODO mismo que lo de arriba
            Attribute matched = ((Defined_Type) leftSide).hasAttribute((FieldID) opnd2());
            ((FieldID) opnd2()).setReference(matched);
        }
        
        return ???;
    }
}


/*
 * struct CS1 {
 *      int c;
 * }
 * 
 * struct CS2{
 *      CS1 b;
 * }
 * 
 * 
 * int main() {
 *      CS2 a;
 *      ...
 * 
 *      a.b.c
 * 
 *      (a.b).c
 * 
 *      a.d()
 * 
 *      a.(3+3)
 * 
 * }
 * 
 * 
 */