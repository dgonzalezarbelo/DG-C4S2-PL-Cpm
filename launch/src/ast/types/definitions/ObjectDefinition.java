package ast.types.definitions;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.operands.AttributeID;
import ast.preamble.Attribute;
import ast.preamble.Constructor;
import ast.preamble.Method;
import ast.types.interfaces.Type;
import exceptions.UndefinedAttributeException;
import exceptions.VisibilityException;
import utils.GoodBoolean;
import utils.GoodInteger;
import utils.Utils;

public abstract class ObjectDefinition extends Definition {
    protected Integer indentation;
    protected List<Attribute> attributes;
    protected ClassFunctions functions;
    
    public ObjectDefinition(String name, List<Attribute> attributes, int row) {
        super(name, row);
        this.attributes = attributes;
    }
    
    public ObjectDefinition(String name, List<Attribute> attributes, List<Constructor> constructors, int row) {
        this(name, attributes, row);
        this.functions = new ClassFunctions(constructors, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Attribute i : attributes)
            str.append(i.toString());
        for (Constructor f : functions.getConstructors())
            str.append('\n' + f.toString());
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
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        for (Attribute a : attributes)
            a.propagateStaticVars(g, s);
        functions.propagateStaticVars(g, s);
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
        return this.type;
    }

    @Override
    public void bind() {
        symbolsTable.setCurrentDefinition(this.definitionName);
        symbolsTable.newScope();

        try {
            super.bind();
            for (Attribute d : attributes)
                d.bind();
            
            for (Constructor c : functions.getConstructors()) {
                if (!c.getDefinitionName().equals(this.definitionName)) {
                    System.out.format("The constructor's name '%s' doesn't match the name of the struct '%s' at row %d\n", c.getDefinitionName(), this.definitionName, c.getRow());
                    continue;
                }
                c.bind();
            }
            // We don't call the super method so that we don't have to close the scope
            for (Method m : functions.getMethods())
                m.bind();
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }

        symbolsTable.closeScope();
        symbolsTable.setCurrentDefinition("");
    }

    @Override
    public void checkType() throws Exception {
        try {
            for (Attribute a : attributes)
                a.checkType();
            for (Constructor c : functions.getConstructors())
                c.checkType();
            for (Method f : functions.getMethods())
                f.checkType();
            functions.checkForDuplicates(); // We check if the constructors are well defined or if there are duplicates (in which case we remove the duplicated definitions)
        } catch (Exception e) {
            System.out.println("Struct " + definitionName + " definition failed");
        }
    }

    public Attribute hasAttribute(AttributeID name, boolean insideClass) throws Exception {
        for (Attribute a : attributes)
            if (a.getVarname().equals(name.toString())) {
                if (!insideClass && !a.isPublic())
                    throw new VisibilityException(String.format("The attribute %s is not public", a.getVarname()));
                return a;
            }
        throw new UndefinedAttributeException("There is no attribute by the name " + name.toString() + " inside " + this.definitionName);
    }

    @Override
    public void maxMemory(GoodInteger c, GoodInteger maxi) {
        maximumMemory.setValue(0);
        GoodInteger curr = new GoodInteger(0);                           // FIXME igual no hay que pasar un copia y hay que pasar el de arriba, darle una vuelta
        for (Attribute a : attributes) {
            a.maxMemory(curr, maximumMemory);       // Only the declarations will change the curr value
            if(curr.toInt() > maximumMemory.toInt())
                maximumMemory.setValue(curr.toInt());
        }
        functions.maxMemory(null, null);
    }

    @Override
    public void computeOffset(Delta delta) {
        delta.pushScope(0);
        for (Attribute a : attributes)
            a.computeOffset(delta);
        functions.computeOffset(delta);
        delta.popScope();
    }

    @Override
    public void generateCode(Josito jose) {
        functions.generateCode(jose);
    }
}
