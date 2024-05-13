package ast.sentences.declarations;

import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.Expression;
import ast.expressions.operands.ConstructorCall;
import ast.types.interfaces.Pointer_Type;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import utils.GoodBoolean;

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
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        if (constructor != null)
            constructor.propagateStaticVars(g, s);
    }

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
        
        throw new UnsupportedOperationException("Unimplemented method 'opnd1'");
    }

    @Override
    public Expression opnd2() {
        
        throw new UnsupportedOperationException("Unimplemented method 'opnd2'");
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'generateAddress'");
    }

    @Override
    public void generateValue(Josito jose) throws Exception {
        Pointer_Type cast = (Pointer_Type)type;
		Type_T t = cast.getInnerType().getKind();
		switch (t) {
            case INT:
            case BOOL:
			case POINTER:
				jose.allocate_heap(type.getSize()); // We just have to allocate 4B in the heap and get the address for the pointer that will store it
				break;
            case CLASS:
            case STRUCT:
				// In this case, the returned value is the object reference to copy it later, so with generateAddress everything is done
                constructor.generateValue(jose);
                jose.copy_to_heap(cast.getInnerType().getSize()); // After this, the address in the heap is already at the top of the stack, and that is the value we wanted, so the assignation will be done properly now
                break;
            case ARRAY: 
            case CONST: // This will never happen
            default:
                break;
        }
    }
}
