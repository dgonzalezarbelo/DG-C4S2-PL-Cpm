package ast.types.interfaces;

public class Pointer_Type extends Envelope_Type {
    private static final int BYTES_SIZE = 4;
    
    public Pointer_Type(Type type, int row) {
        super(type, row);
    }

    public String toString() {return inner_type.toString() + "~";}

    @Override
    public boolean canBeAssigned(Type other) {
        if (other == null)
            return false;
        else if(Envelope_Type.class.isAssignableFrom(other.getClass()))
            return this.getInnerType().canBeAssigned(((Envelope_Type) other).getInnerType());
        else
            return false;
    }

    @Override
    public String getTypename() {
        return this.inner_type.getTypename() + "~";
    }

    @Override
    public void calcSize() { 
        maximumMemory.setValue(BYTES_SIZE);
    }
}
