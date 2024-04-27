package ast.sentences.declarations;

import ast.Expression;
import ast.KindE;
import ast.types.Type;

public class New_Op extends Expression {
    Expression constructor; // In case this attribute is not null then it has to be a constructor, otherwise it will not make sense
    Type type;
  
    public New_Op(Expression constructor) {
        this.constructor = constructor;
    }
    
    public New_Op(Type type) {
        this.type = type;
    }
    
    public String toString() {return "niu" + (constructor != null ? constructor.toString() : type.toString());}
    @Override
    public KindE kind() {
        throw new UnsupportedOperationException("Unimplemented method 'kind'");
    }
  
    @Override
    public void bind() {
        if (type != null)
          type.bind();
        if (constructor != null)
          constructor.bind();
    }
}
