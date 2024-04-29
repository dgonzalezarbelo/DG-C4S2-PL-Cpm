package ast.types;

public class Pointer_Type extends Envelope_Type {
    
    public Pointer_Type(Type type, int row) {
        super(type, row);
    }

    public String toString() {return "~" + inner_type.toString();}

    @Override
    public boolean equals(Type other) {
        if(other.getClass().isAssignableFrom(Envelope_Type.class))
            return this.getInnerType().equals(((Envelope_Type) other).getInnerType());
        else
            return false;
    }
}