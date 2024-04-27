package ast.preamble;

import java.util.List;

public class ClassFunctions {
    private List<Constructor> constructors;
    private List<Function> methods;

    public ClassFunctions(List<Constructor> constructors, List<Function> methods) {
        this.constructors = constructors;
        this.methods = methods;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public List<Function> getMethods() {
        return methods;
    }

    public void propagateIndentation(int indent) {
        for (Function f : this.constructors)
            f.propagateIndentation(indent);
        for (Function f : this.methods)
            f.propagateIndentation(indent);
    }
}
