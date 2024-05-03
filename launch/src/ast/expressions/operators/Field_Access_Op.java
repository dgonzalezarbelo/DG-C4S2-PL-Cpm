package ast.expressions.operators;

import ast.expressions.EBin;
import ast.expressions.Expression;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.preamble.Visibility;
import ast.types.Defined_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.InvalidTypeException;
import exceptions.VisibilityException;
import ast.expressions.values.FieldID;
import ast.expressions.values.ThisID;

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
            throw new InvalidTypeException("Field access operator '.' only applicable to classes or structs at row " + this.row); 
        if (opnd2().getClass().equals(MethodCall.class)) {
            try {
                Method matched = ((Defined_Type) leftSide).hasMethod((MethodCall) opnd2()); 
                ((MethodCall) opnd2()).matchedFunction = matched;
                return matched.getType();
            } catch (Exception e) {
                throw new VisibilityException(e.getMessage() + " at row " + this.row);
            }
        }
        else if (opnd2().getClass().equals(FieldID.class)) {
            try {
                boolean isThis = opnd1().getClass().equals(ThisID.class);
                Attribute matched = ((Defined_Type) leftSide).hasAttribute((FieldID) opnd2(), isThis);
                ((FieldID) opnd2()).setReference(matched);
                return matched.getType();
            } catch (Exception e) {
                throw new VisibilityException(e.getMessage() + " at row " + this.row);
            }
        }
        return null;
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