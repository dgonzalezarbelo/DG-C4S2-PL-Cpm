package ast.expressions.operators;

import ast.Josito;
import ast.expressions.BinaryExpression;
import ast.expressions.Expression;
import ast.types.interfaces.Int_Type;
import ast.types.interfaces.Pointer_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.InvalidDirectionException;
import exceptions.MatchingTypeException;
import exceptions.UnexpectedTypeException;

public class Sum_Op extends BinaryExpression {
    public Sum_Op(Expression opnd1, Expression opnd2, int row) {
        super(opnd1, opnd2, row);
        this.type = new Int_Type(row);
        this.operator = Operator_T.ADD;
        
    }
    
    public String toString() {return opnd1().toString() + " + " +opnd2().toString();}

    @Override
    public void bind() {
        opnd1().bind();
        opnd2().bind();
        this.type.bind();
    }

    @Override
    public void checkType() throws Exception { 
        super.checkType();
        Type left = opnd1().getType();
        Type right = opnd2().getType();
        //TODO Aqui vamos a tener que hacer algo especial para el caso puntero-entero (el orden no importa)
        if (left.getKind() != right.getKind()) {
            if (left.getKind() == Type_T.INT && right.getKind() == Type_T.POINTER) {
                Type aux = left;
                left = right;
                right = aux;
            }
            if (left.getKind() == Type_T.POINTER && right.getKind() == Type_T.INT) {
                this.type = left;
            }
            else
                throw new MatchingTypeException(String.format("'+' operands '%s' and '%s' do not have the same type", opnd1().toString(), opnd2().toString()));
        }
        if (left.getKind() != Type_T.INT)
            throw new UnexpectedTypeException(String.format("'%s' was expected but '%s' was read", Type_T.INT.name(), left.getKind().name()));
        this.type.checkType(); 
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        throw new InvalidDirectionException("Sum operation is not directionable");
    }

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E
        Type left = opnd1().getType();
        Type right = opnd2().getType();
        if (left.getKind() == Type_T.INT && right.getKind() == Type_T.POINTER) { // int * 4 + address
            opnd1().generateValue(jose);
            jose.createConst(4);
            jose.mul();
            opnd2().generateValue(jose);
            jose.translateOperator(this.operator);
        }
        else if (left.getKind() == Type_T.POINTER && right.getKind() == Type_T.INT) { // address + int * 4
            opnd1().generateValue(jose);
            opnd2().generateValue(jose);
            jose.createConst(4);
            jose.mul();
            jose.translateOperator(this.operator);
        }
        else
            super.generateValue(jose);
    }
}
