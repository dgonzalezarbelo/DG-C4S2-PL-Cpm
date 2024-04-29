package ast.preamble;

import java.util.List;

import ast.ASTNode;
import ast.Utils;
import ast.expressions.Expression;
import ast.sentences.Block;
import ast.sentences.declarations.Declaration;
import ast.types.Type;
import exceptions.DuplicateDefinitionException;

public class Function extends Definition {
    protected List<Declaration> args;
    protected Block body;
    protected Type return_t;
    protected Expression return_var;

    public Function(String name, List<Declaration> args, Type return_t, Block body, Expression return_var, int row) {
        super(name, row);
        this.args = args;
        this.return_t = return_t;
        this.body = body;
        this.return_var = return_var;
    }

    @Override
    public String toString() {
        if(this.indentation == null)
            this.propagateIndentation(0);
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("Function: " + id + "\n");
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
            Program.symbolsTable.insertFunction(this.id, this);
            Program.symbolsTable.setCurrentDefinition(this.id);
            propagateBind();
            Program.symbolsTable.setCurrentDefinition("");
        }
        catch (DuplicateDefinitionException e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
    }
    
    protected void propagateBind() {
        Program.symbolsTable.newScope();
        for (Declaration d : args) 
        d.bind(); 
        if (return_t != null)
        return_t.bind();
        body.bind();
        if (return_var != null)
        return_var.bind();
        Program.symbolsTable.closeScope();
    }
    
    @Override
    public List<ASTNode> getReferences() {
        return null; // This method will not be used
    }
    
    @Override
    public Type checkType() throws Exception {
        body.checkType();
        return return_var.checkType();
    }
    
    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        this.body.propagateIndentation(indent + 1);
    }
}
