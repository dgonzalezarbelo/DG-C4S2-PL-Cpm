package ast.sentences.declarations;

import ast.expressions.Expression;
import ast.expressions.operators.ConstructorCall;
import ast.types.Pointer_Type;
import ast.types.Type;
import ast.types.Type.Type_T;

public class New_Op extends Expression {
    Type type;
    ConstructorCall constructor; // In case this attribute is not null then it has to be a constructor, otherwise it will not make sense
  
    public New_Op(ConstructorCall constructor, int row) { // new for constructors [niu Alumno()]
        this.constructor = constructor;
        this.row = row;
    }
    
    public New_Op(Type type, int row) { // new for  [niu int]
        this.type = type;
        this.row = row;
    }
    
    public String toString() {return "niu " + (constructor != null ? constructor.toString() : type.toString());}
  
    @Override
    public void bind() {
        if (type != null)
            type.bind();
        if (constructor != null)
            constructor.bind();
    }

    @Override
    public Type checkType() throws Exception {
        Type t = (constructor != null ? constructor.checkType() : this.type);
        if (t.getKind() != Type_T.ARRAY)
            t = new Pointer_Type(t, row);
        return t;
    }
}
