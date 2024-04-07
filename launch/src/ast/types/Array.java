package ast.types;

import ast.Expression;

public class Array extends Type {
    private Expression dim;
    private Type inner_type;

    public Array(Expression dim) {
        super(Type_T.ARRAY);
        this.dim = dim;
    }

    public Type_T type() {
        return Type_T.ARRAY;
    }

    public void setType(Type t) {
        this.inner_type = t;
    }

    public static Array updateTypes(Array newArray, Array prevArray) {
        /***
         * Consider the following productions:
         * DECLARATION ::= TYPE:t ID:i ARRAY_CONSTRUCTOR:array_t ; 
         * ARRAY_CONSTRUCTOR ::= ARRAY_CONSTRUCTOR SQ_BRACKET_OPEN OPT_INTEGER SQ_BRACKET_CLOSE ;
         * When declaring a multidimensional array, due to using left recursion,
         * if you declare, for example, int[5][5],
         * the left array will be the first one generated (because of recursion), but it will not know it's type. This is prevArray
         * Now, back to the definition, the right [5] is generating another dimension. We will consider this to be newArray,
         * and we will define this to be the type of prevArray, so that it will now be an "array of arrays".
         * 
         * Use:
         * DECLARATION                      ::= TYPE:t ID:id ARRAY_CONSTRUCTOR:array
         * {: RESULT = Declaration.manageDeclaration(t, id, array); :};
         * ARRAY_CONSTRUCTOR                ::= ARRAY_CONSTRUCTOR:prevArray SQ_BRACKET_OPEN OPT_INTEGER:i SQ_BRACKET_CLOSE
         * {: newArray = new Array(i); RESULT = Array.updateTypes(newArray, prevArray); :};
         * ARRAY_CONSTRUCTOR                ::= /EMPTY/
         * {: RESULT = null; :};
         */
        if (prevArray != null) {
            prevArray.setType(newArray);
            return prevArray;
        }
        else
            return newArray;
    }

    public void propagateType(Type t) {
        // If the inner_type is not null then it means it has been previously updated to be another array, hence the casting in the else statement
        if (inner_type == null)
            this.inner_type = t;
        else {
            Array casting = (Array)this.inner_type;
            casting.propagateType(t);
        }
    }

    public String toString() {return "(Dim: " + dim.toString() + ",Array: " + inner_type.toString() + ")";}
}
