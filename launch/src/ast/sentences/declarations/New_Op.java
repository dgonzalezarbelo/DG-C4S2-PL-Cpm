package ast.sentences.declarations;

import ast.expressions.Expression;
import ast.types.Type;

public class New_Op extends Expression {
    Type type;
    Expression constructor; // In case this attribute is not null then it has to be a constructor, otherwise it will not make sense
  
    public New_Op(Expression constructor, int row) { // new for constructors [niu Alumno()]
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
        return this.type;
    }
}
