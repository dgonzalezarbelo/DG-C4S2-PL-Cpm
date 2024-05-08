package ast.preamble;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Josito;
import ast.Utils;
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
            Program.symbolsTable.insertFunction(this.definitionName, this);
            propagateBind();
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }

    protected void propagateBind() {
        Program.symbolsTable.newScope();
        for (Argument d : args) 
            d.bind(); 
        if (return_t != null)
            return_t.bind();
        body.bind();
        if (return_var != null)
            return_var.bind();
        Program.symbolsTable.closeScope();
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
            for (Argument a : args)
                a.checkType();
            body.checkType();
            return_var.checkType();             // TODO esto peta si es una función que no devuelve nada

            Type returnType = return_var.getType();
            if(!returnType.canBeAssigned(return_t)) {
                throw new MatchingTypeException(String.format("The type of the returning expression at function %s '%s' doesnt match the typeof the declared return type '%s'", this.definitionName, returnType, this.return_t));
            }
        }
        catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
        this.type = return_t;
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
    public void maxMemory(Integer c, Integer maxi) {
        maximumMemory = Josito.NUM_FUNC_POINTERS_SIZE;
        Integer curr = 0;                           // FIXME igual no hay que pasar un copia y hay que pasar el de arriba, darle una vuelta
        for (Argument a : args) {
            a.maxMemory(curr, maximumMemory);       // Only the declarations will change the curr value
            if(curr > maximumMemory)
                maximumMemory = curr;
        }
        body.maxMemory(curr, maximumMemory);
        if (return_t != null)
            return_t.maxMemory(null, null);
        maximumMemory += return_t.getSize();
        if (maxi != null)
            maxi += maximumMemory;
    }

    @Override
    public void computeOffset(Delta delta) {
        delta.pushScope();
        for (Argument a : args)
            a.computeOffset(delta);
        body.computeOffset(delta);
        if (return_var != null)             // FIXME igual esto no se devuelve por la pila
            return_var.computeOffset(delta);
        delta.popScope();
    }

    /*
    @Override
    public void generateCode(Josito jose) { // TODO Este era el anterior
        jose.funcHeader(this.definitionName);
        for (Argument a : args)
            a.generateCode(jose);
        // TODO poner codigo del result
        body.generateCode(jose);
        return_var.generateCode(jose);  // TODO se supone que aquí calculas el valor de retorno
        jose.funcTail();                // TODO y aquí se apila si procede para ser devuelto
    } */

    @Override
    public void generateCode(Josito jose) { // TODO hecho por javi, quiza revisar
        WASMId = jose.getAndIncrementId();  // Get the unique function Id
        jose.funcHeader(WASMId);
        if (return_t != null)               // Check if its a typed function or not
            jose.funcResult();
        body.generateCode(jose);
        if (return_var != null)
            return_var.generateValue(jose);  // TODO se supone que aquí calculas el valor de retorno
        jose.funcTail();                // TODO y aquí se apila si procede para ser devuelto
    }
}