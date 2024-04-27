package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import exceptions.DuplicateDefinitionException;

public class SymbolsTable {
    private List<Map<String, ASTNode>> stackScope;              // definitions table (id -> Declaration_Node)
    private List<Map<String, List<ASTNode>>> functionsScope;    // functions table (id -> List<function>) [due tooverride]
    private Map<String, List<ASTNode>> definitions;             // user-definitions table (id -> Lista de constructores / constructor / expresion tipo)

    public SymbolsTable() {
        this.stackScope = new LinkedList<>();
        this.functionsScope = new LinkedList<>();
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
        if (stackScope.getFirst().containsKey(id)) { // You can not redefine a variable in the same scope it has already been defined
            throw new DuplicateDefinitionException(String.format("The variable %s is already defined", id));
        }
        stackScope.getFirst().put(id, node);
    }

    public void insertFunction(String id, ASTNode node) throws DuplicateDefinitionException {
        if (definitions.containsKey(id))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        Map<String, List<ASTNode>> scope = functionsScope.getFirst();
        if (!scope.containsKey(id))
            scope.put(id, new ArrayList<>());
        scope.get(id).add(node);
    }

    public void insertDefinitions(String id, List<ASTNode> nodes) throws DuplicateDefinitionException { 
        /* 
        When a new user-type is defined we consider that type as a keyword in order
        to prevent variables or new user-types with the same name 
        */ 
        if (!definitions.containsKey(id))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        definitions.put(id, nodes);
    }

    public ASTNode getReference(String id) throws Exception {
        for (Map<String, ASTNode> m : stackScope) {
            if (m.containsKey(id))
                return m.get(id);
        }
        throw new Exception("Required reference " + id + " not found");
    }

    public List<ASTNode> getDefinitions(String id) {
        if (definitions.containsKey(id))
            return definitions.get(id);
        else
            return functionsScope.getFirst().get(id);
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