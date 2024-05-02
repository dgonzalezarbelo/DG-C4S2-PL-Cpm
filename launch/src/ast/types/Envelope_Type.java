package ast.types;

public abstract class Envelope_Type extends Type {
    protected Type inner_type;

    public Envelope_Type(Type type, int row) {
        super(Type_T.POINTER, row); 
        this.inner_type = type;     
    }

    public Type getInnerType() {
        return this.inner_type;
    }

    @Override
    public void bind() {
        this.inner_type.bind();
    }

    @Override
    public Type checkType() throws Exception {
        this.inner_type.checkType();
        return super.checkType();
    }
}
