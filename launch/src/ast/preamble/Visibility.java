package ast.preamble;

public abstract class Visibility {

    protected int row;

    protected enum Visibility_T {
        PUBLIC, PRIVATE;

        public String toString() {
            return this.name().toLowerCase();
        }
    }

    protected Visibility_T visibility;

    public Visibility(Visibility_T v, int row) {
        this.visibility = v;
        this.row = row;
    }

    public Visibility_T getVisibility() {
        return this.visibility;
    }

    @Override
    public String toString() {
        return visibility.toString();
    }        
}
