package ast.preamble;

public abstract class Visibility {

    protected enum Visibility_T {
        PUBLIC, PRIVATE;

        public String toString() {
            return "Tipo: " + this.name().toLowerCase();
        }
    }

    protected Visibility_T visibility;

    public Visibility(Visibility_T v) {
    this.visibility = v;   
    }

    @Override
    public String toString() {
        return visibility.toString();
    }        
}
