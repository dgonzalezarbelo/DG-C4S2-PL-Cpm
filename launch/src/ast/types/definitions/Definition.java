package ast.types.definitions;

import java.util.List;

import ast.ASTNode;
import ast.ASTNodeTypable;
import ast.Delta;
import ast.Indentable;
import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.preamble.Attribute;
import ast.preamble.Method;
import ast.types.interfaces.Type;
import exceptions.DuplicateDefinitionException;
import utils.GoodBoolean;
import utils.Utils;

public abstract class Definition extends ASTNodeTypable implements Indentable {
    protected Integer indentation;
    protected String definitionName;
    
    public Definition(String name, int row) {
        this.indentation = null;
        this.definitionName = name;
        this.row = row;
    }

    public String getDefinitionName() {
        return this.definitionName;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public void bind() {
        try {
            symbolsTable.insertDefinitions(this.definitionName, this);
        } catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }
    }
    public abstract List<ASTNode> getConstructors();
    public abstract Attribute hasAttribute(AttributeID name, boolean insideClass) throws Exception;
    public abstract Method hasMethod(FunctionCall mc) throws Exception;

    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
    }

    public void propagateIndentation(int indent) {
        // Nothing to do by default
    }

    public int getSize() {
        return maximumMemory.toInt();
    }

    @Override
    public void computeOffset(Delta delta) {
        // Nothing to do
    }

    @Override
    public void generateCode(Josito jose) {
        // Nothing to do
    }
}