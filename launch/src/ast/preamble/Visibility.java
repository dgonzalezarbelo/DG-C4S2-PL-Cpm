package ast.preamble;

public abstract class Visibility {

    protected enum Visibility_T {
        PUBLIC, PRIVATE;

        public String toString() {
            return this.name().toLowerCase();
        }
    }

    protected Visibility_T visibility;

    public Visibility(Visibility_T v) {
        this.visibility = v;
    }

    public Visibility_T getVisibility() {
        return this.visibility;
    }

    @Override
    public String toString() {
        return visibility.toString();
    }        
}
