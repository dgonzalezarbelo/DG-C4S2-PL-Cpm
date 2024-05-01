package ast.preamble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFunctions {
    private List<Constructor> constructors;
    private List<Method> methods;
    private Map<String, Constructor> constructorsHash;
    private Map<String, Method> methodsHash;



    public ClassFunctions(List<Constructor> constructors, List<Method> methods) {
        this.constructors = constructors;
        this.methods = methods;
        this.constructorsHash = new HashMap<>();
        this.methodsHash = new HashMap<>();
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

    public void checkForDuplicates() {
        List<Constructor> newConstructors = new ArrayList<>();
        List<Method> newMethods = new ArrayList<>();
        for (Constructor c : constructors) {
            String hash = c.hash();
            if (constructorsHash.containsKey(hash))
                System.out.format("There is a duplicated constructor definition at line %d\n", c.getRow());
            else {
                constructorsHash.put(hash, c);
                newConstructors.add(c);
            }
        }
        for (Method m : methods) {
            String hash = m.hash();
            if (methodsHash.containsKey(hash))
                System.out.format("There is a duplicated constructor definition at line %d\n", m.getRow());
            else {
                methodsHash.put(hash, m);
                newMethods.add(m);
            }
        }
        this.constructors = newConstructors;
        this.methods = newMethods;
    }

    public void propagateIndentation(int indent) {
        for (Function f : this.constructors)
            f.propagateIndentation(indent);
        for (Function f : this.methods)
            f.propagateIndentation(indent);
    }
}
