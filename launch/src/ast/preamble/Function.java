package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.Expression;
import ast.expressions.operands.AttributeID;
import ast.expressions.operands.FunctionCall;
import ast.sentences.Block;
import ast.types.definitions.Definition;
import ast.types.interfaces.Type;
import exceptions.DuplicateDefinitionException;
import exceptions.MatchingTypeException;
import exceptions.UndefinedAttributeException;
import exceptions.UndefinedFunctionException;
import utils.GoodBoolean;
import utils.GoodInteger;
import utils.Utils;

public class Function extends Definition {
    protected Integer indentation;
    protected Integer WASMId; // Function Id in wasm to recognize it
    protected List<Argument> args;
    protected Block body;
    protected Type return_t;
    protected Expression return_var;

    public Function(String name, List<Argument> args, Type return_t, Block body, Expression return_var, int row) {
        super(name, row);
        this.args = args;
        this.return_t = return_t;
        this.body = body;
        this.return_var = return_var;
        this.WASMId = -1;
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        for (Argument a : args)
            a.propagateStaticVars(g, s);
        if (body != null)
            body.propagateStaticVars(g, s);
        if (return_t != null)
            return_t.propagateStaticVars(g, s);
        if (return_var != null)
            return_var.propagateStaticVars(g, s);
    }

    @Override
    public String toString() { // TODO no imprimimos los argumentos en el toString de la funcion
        if(this.indentation == null)
            this.propagateIndentation(0);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Function: " + definitionName + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Tipo: " + return_t + "\n");
        Utils.appendIndent(str, indentation);
        str.append("Cuerpo: \n" + body);
        Utils.appendIndent(str, indentation);
        str.append("Return: " + return_var + "\n");
        return str.toString();
    }

    @Override
    public void bind() {
        try {
            symbolsTable.insertFunction(this.definitionName, this);
            propagateBind();
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }
    }

    protected void propagateBind() {
        symbolsTable.newScope();
        for (Argument d : args) 
            d.bind(); 
        if (return_t != null)
            return_t.bind();
        body.bind();
        if (return_var != null)
            return_var.bind();
        symbolsTable.closeScope();
    }

    public List<Argument> getArgumentsList() {
        return this.args;
    }

    @Override
    public List<ASTNode> getConstructors() {
        return null; // This method will not be used
    }

    public List<Type> getArgumentTypes() throws Exception {
        List<Type> types = new ArrayList<>();
        for (Argument a : args)
            types.add(a.getType());
        return types;
    }
    public Integer getWASMId() {
        return this.WASMId;
    }

    @Override
    public void checkType() throws Exception {
        try {
            if(return_t != null)
                return_t.checkType();
            this.type = return_t;
            for (Argument a : args)
                a.checkType();
            body.checkType();
            if(return_var != null) {
                return_var.checkType();
                Type returnType = return_var.getType();
                if(!returnType.canBeAssigned(return_t)) {
                    throw new MatchingTypeException(String.format("The type of the returning expression at function %s '%s' doesnt match the typeof the declared return type '%s'", this.definitionName, returnType, this.return_t));
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }        
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        this.body.propagateIndentation(indent + 1);
    }

    @Override
    public Attribute hasAttribute(AttributeID name, boolean insideClass) throws Exception {
        throw new UndefinedAttributeException("There are no attributes defined inside a function");
    }

    @Override
    public Method hasMethod(FunctionCall m) throws Exception {
        throw new UndefinedFunctionException("There are no methods defined inside a function");
    }

    @Override
    public void maxMemory(GoodInteger c, GoodInteger maxi) {
        maximumMemory.setValue(Josito.NUM_FUNC_POINTERS_SIZE);
        GoodInteger curr = new GoodInteger(this.maximumMemory.toInt());
        for (Argument a : args) {
            a.maxMemory(curr, maximumMemory);               // Only the declarations will change the curr value
            if(curr.toInt() > maximumMemory.toInt())
                maximumMemory.setValue(curr.toInt());
        }
        body.maxMemory(curr, maximumMemory);
        if (return_t != null) {
            return_t.maxMemory(null, null);
            maximumMemory.setValue(maximumMemory.toInt() + return_t.getSize());
        }
        if (maxi != null)
            maxi.setValue(maxi.toInt() + maximumMemory.toInt());
    }

    @Override
    public void computeOffset(Delta delta) {
        delta.pushScope(Josito.NUM_FUNC_POINTERS_SIZE); // To have in consideration the DL and reference in the local memory of the function
        for (Argument a : args)
            a.computeOffset(delta);
        body.computeOffset(delta);
        if (return_var != null)
            return_var.computeOffset(delta);
        delta.popScope();
    }

    @Override
    public void generateCode(Josito jose) {
        if (WASMId != 0)                        // If the WASMId is 0 then this function is the Main, so we keep the WASMId 0
            WASMId = jose.getAndIncrementId();  // Get the unique function Id
        jose.funcHeader(WASMId);
        if (return_t != null)                   // Check if its a typed function or not
            jose.funcResult();
        body.generateCode(jose);
        if (return_var != null)
            try {
                return_var.generateValue(jose);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Utils.printErrorRow(return_var.getRow());
            }
        jose.funcTail();
    }

    public void setAsMain() {
        this.WASMId = 0;
    }
}