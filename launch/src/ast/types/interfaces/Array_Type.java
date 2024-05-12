package ast.types.interfaces;

import java.util.ArrayList;
import java.util.List;

import ast.SymbolsTable;
import ast.expressions.Expression;
import ast.expressions.operands.Int_Value;
import ast.expressions.operands.Literal;
import exceptions.InvalidTypeException;
import utils.GoodBoolean;

public class Array_Type extends Envelope_Type {
    private Expression dim;
    private Type outer_type;            // In case this array is also the type of another "bigger" array (in terms of dimensions)
    private Type inner_terminal_type;   // Example: int[7][4][8] has "int" as inner_terminal_type 
    private int array_dimension;        // Example: int[7][4][8] has an array_dimension of 3
    private boolean isDynamic;

    public Array_Type(Expression dim, int row) {
        super(null, row);
        this.kind = Type_T.ARRAY;
        this.dim = dim;
        this.outer_type = null;
        this.inner_terminal_type = null;
        this.array_dimension = 0;
        this.isDynamic = false;
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        if (dim != null)
            dim.propagateStaticVars(g, s);
        if (outer_type != null)
            outer_type.propagateStaticVars(g, s);
        if (inner_terminal_type != null)
            inner_terminal_type.propagateStaticVars(g, s);
    }

    public void setInnerType(Type t) {
        this.inner_type = t;
    }

    public boolean isSubarray() {
        return this.outer_type != null;
    }

    public void setOuterType(Type t) {
        this.outer_type = t;
    }

    public int getArrayDimenssion() {
        return this.array_dimension;
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
         * //TODO Update this
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

    public Type getInnerTerminalType() {
        return this.inner_terminal_type;
    }

    public Expression getDim() {
        return dim;
    }

    public List<Expression> getDimenssions() {
        List<Expression> dimenssions = new ArrayList<>();
        dimenssions.add(dim);
        if (array_dimension > 1) {
            List<Expression> inner_dimenssions = ((Array_Type) inner_type).getDimenssions();
            List<Expression> ret = new ArrayList<>();
            ret.add(dim);
            for (Expression e : inner_dimenssions)
                ret.add(e);
            return ret;
        }
        return dimenssions;
    }

    @Override
    public String getTypename() {
        return inner_type.getTypename() + "[" + this.dim + "]";
    }

    @Override
    public void bind() {
        if (this.dim != null)
            this.dim.bind();
        super.bind();
    }

    @Override
    public void checkType() throws Exception {
        if (this.dim != null)
            this.dim.checkType();
        super.checkType();

        // Now we check if the array is static, dynamic of wrongly declared
        List<Expression> dimenssions = getDimenssions();
        boolean _static = true, _dynamic = true;
        for (Expression e : dimenssions) {
            if (e == null)
                _static = false;
            else
                _dynamic = false;
        }
        if (!_static && !_dynamic)
            throw new InvalidTypeException("The array is neither static nor dynamic");
        setDynamic(_dynamic); // We propagate the dynamic boolean to the inner types
    }

    public void setDynamic(boolean isDynamic) {
        this.isDynamic = isDynamic;
        if (array_dimension > 1)
            ((Array_Type)inner_type).setDynamic(isDynamic);
    }

    public boolean isDynamic() {
        return this.isDynamic;
    }
    
    public void calcSize() {
        if (!this.isDynamic) { // If the array is static
            inner_type.calcSize();
            Integer inner_size = inner_type.getSize();
            if (dim != null) {
                Literal l = ((Const_Type) dim.getType()).getConstValue();
                int dim_value = ((Int_Value)l).num();
                maximumMemory.setValue(dim_value * inner_size);
            }
            else
                maximumMemory.setValue(0); //FIXME No se si esto estara bien
        }
        else { // We have to save the pointer, the size of the array and the size of every dimenssion
            List<Expression> dimenssions = getDimenssions();
            maximumMemory.setValue((2 + dimenssions.size()) * 4);
            if (array_dimension > 1)
                ((Array_Type)inner_type).calcSize();
        }
    }
}