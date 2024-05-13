package ast.sentences.instructions;

import ast.sentences.Block;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;
import exceptions.BooleanConditionException;
import utils.GoodBoolean;
import utils.GoodInteger;
import utils.Utils;
import ast.Delta;
import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.Expression;

public class If_Ins extends Instruction {
    private Block elseBody;

    public If_Ins(Expression cond, Block if_body, Block else_body, int row) {
        super(cond, if_body, row);
        this.elseBody = else_body;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append(Utils.PURPLE + "if" + Utils.RESET + "(" + this.argExpression.toString() + ")\n");
        str.append(body.toString());
        if(!elseBody.empty()) {
            Utils.appendIndent(str, indentation);
            str.append(Utils.PURPLE + "else" + Utils.RESET + '\n');
            str.append(elseBody.toString());
        }
        return str.toString();
    }

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        elseBody.propagateStaticVars(g, s);
    }
    
    @Override
    public void propagateIndentation(int indent) {
        super.propagateIndentation(indent);
        this.elseBody.propagateIndentation(indent + 1);
    }

    @Override
    public void bind() {
        symbolsTable.newScope();
        super.bind();
        symbolsTable.closeScope();
        if (!elseBody.empty()) {
            symbolsTable.newScope();
            elseBody.bind();
            symbolsTable.closeScope();
        }
    }

    @Override
    public void checkType() throws Exception {
        try {
            super.checkType();
            Type condType = argExpression.getType();
            if (condType.getKind() == null || condType.getKind() != Type_T.BOOL)
                throw new BooleanConditionException("'If' condition must be bolean type");
            if (!elseBody.empty())
                elseBody.checkType();
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
            this.errorFlag.setValue(true);
        }
    }

    @Override
    public void maxMemory(GoodInteger c, GoodInteger max) {
        super.maxMemory(c, max);
        if (!elseBody.empty())
            elseBody.maxMemory(c, max);
    }

    @Override
    public void computeOffset(Delta delta) {
        super.computeOffset(delta);
        if (!elseBody.empty())
            elseBody.computeOffset(delta);
    }

    @Override
    public void generateCode(Josito jose) { 
        try {
            argExpression.generateValue(jose);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.printErrorRow(row);
        }
        jose.ifInit();
        body.generateCode(jose);
        if(elseBody != null) {
            jose.elseInit();
            elseBody.generateCode(jose);
        }
        jose.endBlock();  
    }
}
