package ast.sentences.declarations;

import ast.Josito;
import ast.expressions.Expression;
import ast.expressions.operands.ConstructorCall;
import ast.types.interfaces.Pointer_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;

public class New_Op extends Expression {
    private ConstructorCall constructor; // In case this attribute is not null then it has to be a constructor, otherwise it will not make sense

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
    public void checkType() throws Exception {
        Type newType;
        if (constructor != null) {
            constructor.checkType();
            newType = constructor.getType();
        }
        else {
            newType = this.type;
            newType.checkType();
        }
        if (newType.getKind() != Type_T.ARRAY) {
            newType = new Pointer_Type(newType, row);
            newType.checkType();
        }
        this.type = newType;
    }

    @Override
    public Expression opnd1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opnd1'");
    }

    @Override
    public Expression opnd2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opnd2'");
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAddress'");
    }

    // TODO creo que aqui solo hace falta el generateValue (Code_E)
}
