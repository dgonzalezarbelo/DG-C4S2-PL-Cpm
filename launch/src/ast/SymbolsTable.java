package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ast.preamble.Definition;
import ast.types.Id_Type;
import exceptions.DuplicateDefinitionException;
import exceptions.InvalidIdException;
import exceptions.InvalidTypeException;

public class SymbolsTable {
    private LinkedList<Map<String, ASTNode>> stackScope;              // definitions table (id -> Declaration_Node)
    private LinkedList<Map<String, List<ASTNode>>> functionsScope;    // functions table (id -> List<function>) [due tooverride]
    //FIXME El valor del siguiente mapa no es ASTNode y eso no es chulo, hay que ver si es evitable
    private Map<String, Definition> definitions;                      // user-definitions table (id -> Definition_Node)

    public SymbolsTable() {
        this.stackScope = new LinkedList<>();
        this.functionsScope = new LinkedList<>();
        this.definitions = new HashMap<>();
    }

    public void newScope() {
        stackScope.addFirst(new HashMap<>());
        functionsScope.addFirst(new HashMap<>());
    }

    public void closeScope() {
        stackScope.removeFirst();
        functionsScope.removeFirst();
    }

    public void insertSymbol(String id, ASTNode node) throws DuplicateDefinitionException {
        if (definitions.containsKey(id)) // You can not use as an id a reserved word, such as a user defined type
            throw new DuplicateDefinitionException(String.format("The id %s is a reserved word", id));
        if (stackScope.isEmpty())
            stackScope.add(new HashMap<String, ASTNode>());
        if (stackScope.getFirst().containsKey(id)) { // You can not redefine a variable in the same scope it has already been defined
            throw new DuplicateDefinitionException(String.format("The variable %s is already defined", id));
        }
        stackScope.getFirst().put(id, node);
    }

    public void insertFunction(String id, ASTNode node) throws DuplicateDefinitionException {
        if (definitions.containsKey(id))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        if (functionsScope.isEmpty())
            functionsScope.add(new HashMap<String, List<ASTNode>>());
        Map<String, List<ASTNode>> scope = functionsScope.getFirst();
        if (!scope.containsKey(id))
            scope.put(id, new ArrayList<>());
        scope.get(id).add(node);
    }

    public void insertDefinitions(String id, Definition def) throws DuplicateDefinitionException { 
        /* 
        When a new user-type is defined we consider that type as a keyword in order
        to prevent variables or new user-types with the same name 
        */ 
        if (!definitions.containsKey(id))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        definitions.put(id, def);
    }

    public ASTNode getReference(String id) throws InvalidIdException {
        for (Map<String, ASTNode> m : stackScope) {
            if (m.containsKey(id))
                return m.get(id);
        }
        throw new InvalidIdException("Required reference " + id + " not found");
    }

    public List<ASTNode> getMethodsDefinitions(String id) throws InvalidIdException { 
        /*
        We admit inside classes functions overriding outside-defined functions
        thats why we are using addAll method while iterating the Map List
        */
        if (definitions.containsKey(id))
            return definitions.get(id).getReferences();
        else {
            List<ASTNode> list = new ArrayList<>();
            for (Map<String, List<ASTNode>> m : functionsScope) {
                if (m.containsKey(id))
                    list.addAll(m.get(id));
            }
            if (list.isEmpty())
                throw new InvalidIdException("Required reference " + id + " not found");
            return list;
        }
            
    }

    public Definition getDefinition(String id) throws InvalidTypeException {
        if (!definitions.containsKey(id))
            throw new InvalidTypeException(String.format("The type %s does not exist", id));
        return definitions.get(id);
    }

    public boolean doesTypeExist(Id_Type type) {
        return definitions.containsKey(type.getName());
    }
}

/*
//FIXME
clas Alumno {
    public Alumno(int a) {

    }
}

func Alumno(int a) : Alumno { <-- Los cojones (ya está definido)
    ...
}

{Alumno: nodo1, nodo2}

Alumno a = Alumno(0); <-- Se llama a la función
 */