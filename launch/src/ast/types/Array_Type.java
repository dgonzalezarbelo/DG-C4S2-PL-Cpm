package ast.types;

import ast.Expression;

public class Array_Type extends Type {
    private Expression dim;
    private Type inner_type;
    private Type outer_type; // In case this array is also the type of another "bigger" array (in terms of dimensions)
    private Type inner_terminal_type;
    private int array_dimension; // Example: int[7][][8] has an array_dimension of 3

    public Array_Type(Expression dim, int row) {
        super(Type_T.ARRAY, row);
        this.dim = dim;
        this.inner_type = null;
        this.outer_type = null;
        this.inner_terminal_type = null;
        this.array_dimension = 0;
    }

    public Type_T type() {
        return Type_T.ARRAY;
    }

    public void setInnerType(Type t) {
        this.inner_type = t;
    }

    public void setOuterType(Type t) {
        this.outer_type = t;
    }

    public static Array_Type updateTypes(Array_Type newArray, Array_Type prevArray) {
        /*
         * Consider the following productions:
         * DECLARATION ::= TYPE:t ID:i ARRAY_CONSTRUCTOR:array_t ; 
         * ARRAY_CONSTRUCTOR ::= ARRAY_CONSTRUCTOR SQ_BRACKET_OPEN OPT_INTEGER SQ_BRACKET_CLOSE ;
         * When declaring a multidimensional array, due to using left recursion,
         * if you declare, for example, int[4][5],
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
            prevArray.setInnerType(newArray);
            newArray.setOuterType(prevArray);
        }
        return newArray;
    }

    public Array_Type recoverBiggestArray(Type t, int array_dimension) {
        // If the inner_type is not null then it means it has been previously updated to be another array, hence the casting in the else statement
        this.array_dimension = array_dimension;
        if (outer_type != null)
            return ((Array_Type)outer_type).recoverBiggestArray(t, array_dimension + 1);
        this.inner_terminal_type = t;
        return this;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (outer_type == null) str.append(inner_terminal_type.toString());
        str.append("[" + (dim != null ? dim.toString() : "") + "]");
        if (array_dimension > 1) str.append(inner_type.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        if (dim != null)
            dim.bind();
        inner_type.bind();
    }
}
