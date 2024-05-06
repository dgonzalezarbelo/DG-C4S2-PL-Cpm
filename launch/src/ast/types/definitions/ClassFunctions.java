package ast.types.definitions;

import java.util.ArrayList;
import java.util.List;

import ast.Delta;
import ast.preamble.Constructor;
import ast.preamble.Function;
import ast.preamble.Method;
import ast.types.interfaces.Type;

public class ClassFunctions {
    private List<Constructor> constructors;
    private List<Method> methods;

    public ClassFunctions(List<Constructor> constructors, List<Method> methods) {
        this.constructors = constructors;
        this.methods = methods;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public void removeConstructor(Constructor c) {
        constructors.remove(c);
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void removeMethod(Method m) {
        constructors.remove(m);
    }

    public void checkForDuplicates() throws Exception {
        List<Function> newConstructors = new ArrayList<>();
        List<Function> newMethods = new ArrayList<>();
        boolean duplicate;
        for (Constructor c : constructors) {
            duplicate = duplicateFunction(c, newConstructors);
            if (duplicate)
                System.out.format("There is a duplicated constructor definition at line %d\n", c.getRow());
            else
                newConstructors.add(c);
        }
        for (Method m : methods) {
            duplicate = duplicateFunction(m, newMethods);
            if (duplicate)
                System.out.format("There is a duplicated constructor definition at line %d\n", m.getRow());
            else
                newMethods.add(m);
        }
        this.constructors = new ArrayList<>();
        for (Function c : newConstructors)
            this.constructors.add((Constructor) c);
        this.methods = new ArrayList<>();
        for (Function m : newMethods)
            this.methods.add((Method) m);
    }

    private boolean duplicateFunction(Function f, List<Function> functions) throws Exception {
        List<Type> types1 = f.getArgumentTypes();
        for (Function f2 : functions) {
            boolean match = true;
            if (!f2.getDefinitionName().equals(f.getDefinitionName()))
                break;
            List<Type> types2 = f2.getArgumentTypes();
            if (types1.size() != types2.size())
                break;
            for (int i = 0; i < types1.size(); i++) {
                if (!types1.get(i).equals(types2.get(i))) {
                    match = false;
                    break;
                }
            }
            if (match)
                return true;
        }
        return false;
    }

    public void propagateIndentation(int indent) {
        for (Function f : this.constructors)
            f.propagateIndentation(indent);
        for (Function f : this.methods)
            f.propagateIndentation(indent);
    }

    public void maxMemory(Integer c, Integer max) {
        for (Function _c : constructors)
            _c.maxMemory(null, null);
        for (Function _m : methods)
            _m.maxMemory(null, null);
    }

    public void computeOffset(Delta delta) {
        for (Constructor c : constructors)
            c.computeOffset(delta);
        for (Method m : methods)
            m.computeOffset(delta);
    }
}