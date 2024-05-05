package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.operators.MethodCall;
import ast.expressions.values.FieldID;
import ast.types.Defined_Type;
import ast.types.Type;
import ast.types.Type.Type_T;
import exceptions.DuplicateDefinitionException;
import exceptions.UndefinedAttributeException;
import exceptions.UndefinedFunctionException;
import exceptions.VisibilityException;

public class Struct extends Definition {
    protected List<Attribute> attributes;
    protected ClassFunctions functions;
    protected Type definedType;
    
    public Struct(String name, List<Attribute> attributes, int row) {
        super(name, row);
        this.attributes = attributes;
        this.definedType = new Defined_Type(id, row, Type_T.STRUCT, this);
    }
    
    public Struct(String name, List<Attribute> attributes, List<Constructor> constructors, int row) {
        this(name, attributes, row);
        this.functions = new ClassFunctions(constructors, new ArrayList<>());
        this.definedType = new Defined_Type(id, row, Type_T.STRUCT, this);
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        else
            this.propagateIndentation(this.indentation);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Struct: " + id + "\n");
        for (Attribute i : attributes) {
            str.append(i.toString());
        }
        for (Constructor f : functions.getConstructors()) {
            str.append('\n' + f.toString());
        }
        return str.toString();
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        for (Attribute d : attributes)
            d.propagateIndentation(indent + 1);
        this.functions.propagateIndentation(indent + 1);
    }
    
    @Override
    public List<ASTNode> getConstructors() {
        List<ASTNode> list = new ArrayList<>();
        for (Constructor c : functions.getConstructors())
            list.add(c);
        return list;
    }

    @Override
    public Type getType() {
        return this.definedType;
    }

    @Override
    public Type getRootType() {
        return getType();
    }

    @Override
    public void bind() {
        Program.symbolsTable.setCurrentDefinition(this.id);
        Program.symbolsTable.newScope();

        try {
            Program.symbolsTable.insertDefinitions(this.id, this);
            for (Attribute d : attributes)
                d.bind();
            
            for (Constructor c : functions.getConstructors()) {
                if (!c.getId().equals(this.id)) {
                    System.out.format("The constructor's name '%s' doesn't match the name of the struct '%s' at row %d\n", c.getId(), this.id, c.getRow());
                    continue;
                }
                c.bind();
            }
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }

        Program.symbolsTable.closeScope();
        Program.symbolsTable.setCurrentDefinition("");
    }

    @Override
    public Type checkType() throws Exception {
        try {
            for (Attribute a : attributes)
                a.checkType();
            for (Constructor c : functions.getConstructors())
                c.checkType();
            functions.checkForDuplicates(); // We check if the constructors are well defined or if there are duplicates (in which case we remove the duplicated definitions)
        } catch (Exception e) {
            System.out.println("Struct " + id + " definition failed");
        }
        return this.definedType;
    }

    @Override
    public Type_T checkKind() {
        return Type_T.STRUCT;
    }

    @Override
    public Attribute hasAttribute(FieldID name, boolean insideClass) throws Exception {
        for (Attribute a : attributes)
            if (a.getName().equals(name.toString())) {
                if (!insideClass && !a.isPublic())
                    throw new VisibilityException(String.format("The attribute %s is not public", a.getName()));
                return a;
            }
        throw new UndefinedAttributeException("There is no attribute by the name " + name.toString() + " inside " + this.id);
    }

    @Override
    public Method hasMethod(MethodCall mc) throws Exception {
        throw new UndefinedFunctionException("There are no methods inside a struct");
    }      

    @Override
    public void maxMemory(Integer c, Integer max) {
        maximumMemory = 0;
        Integer curr = 0;
        for (Attribute a : attributes)
            a.maxMemory(curr, maximumMemory);
        functions.maxMemory(0, 0);
        maximumMemory = curr;
    }
}