package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ast.types.definitions.Definition;
import exceptions.DuplicateDefinitionException;
import exceptions.InvalidIdException;
import exceptions.InvalidTypeException;

public class SymbolsTable {
    private LinkedList<Map<String, ASTNodeTypable>> symbolsScope;       // definitions table (id -> Declaration_Node / Define)
    private LinkedList<Map<String, List<ASTNode>>> functionsScope;      // functions table (id -> List<function>) [due tooverride]
    private Map<String, Definition> typeDefinitions;                    // user-definitions table (id -> Definition_Node)
    private String currentDefinition;                                   // variable that represent the current defintion you are in 

    public SymbolsTable() {
        this.symbolsScope = new LinkedList<>();
        this.functionsScope = new LinkedList<>();
        this.typeDefinitions = new HashMap<>();
        this.currentDefinition = "";
    }

    public void newScope() {
        symbolsScope.addFirst(new HashMap<>());
        functionsScope.addFirst(new HashMap<>());
    }

    public void closeScope() {
        symbolsScope.removeFirst();
        functionsScope.removeFirst();
    }

    public void insertSymbol(String id, ASTNodeTypable node) throws DuplicateDefinitionException {
        if (typeDefinitions.containsKey(id)) // You can not use as an id a reserved word, such as a user defined type
            throw new DuplicateDefinitionException(String.format("The id %s is a reserved word", id));
        if (symbolsScope.isEmpty())
            symbolsScope.add(new HashMap<String, ASTNodeTypable>());
        if (symbolsScope.getFirst().containsKey(id)) { // You can not redefine a variable in the same scope it has already been defined
            throw new DuplicateDefinitionException(String.format("The variable %s is already defined", id));
        }
        symbolsScope.getFirst().put(id, node);
    }

    public void insertFunction(String id, ASTNode node) throws DuplicateDefinitionException {
        if (typeDefinitions.containsKey(id))
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
        if (typeDefinitions.containsKey(id))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        typeDefinitions.put(id, def);
    }

    public void copyDefinition(String newName, String oldName) throws Exception {
        if (typeDefinitions.containsKey(newName))
            throw new DuplicateDefinitionException("There is a previous definition with the same name");
        if (!typeDefinitions.containsKey(oldName))
            throw new InvalidTypeException(String.format("There is no definition named '%s'", oldName));
        Definition d = typeDefinitions.get(oldName);
        typeDefinitions.put(newName, d);
    }

    public ASTNodeTypable getReference(String id) throws InvalidIdException {
        for (Map<String, ASTNodeTypable> m : symbolsScope) {
            if (m.containsKey(id))
                return m.get(id);
        }
        throw new InvalidIdException("Required reference " + id + " not found");
    }

    public List<ASTNode> getFuncAndConstructsDefinitions(String id) throws InvalidIdException { 
        /*
        We admit inside classes functions overriding outside-defined functions
        thats why we are using addAll method while iterating the Map List
        */
        if (typeDefinitions.containsKey(id))
                return typeDefinitions.get(id).getConstructors();
        else {
            List<ASTNode> list = new ArrayList<>();
            for (Map<String, List<ASTNode>> m : functionsScope) {
                if (m.containsKey(id))
                    list.addAll(m.get(id));
            }
            return list;
        }
    }

    public Definition getDefinition(String id) throws InvalidTypeException {
        if (!typeDefinitions.containsKey(id))
            throw new InvalidTypeException(String.format("The type %s does not exist", id));
        return typeDefinitions.get(id);
    }

    public void setCurrentDefinition(String s) {
        this.currentDefinition = s;
    }

    public String getCurrentDefinition() {
        return this.currentDefinition;
    }
}