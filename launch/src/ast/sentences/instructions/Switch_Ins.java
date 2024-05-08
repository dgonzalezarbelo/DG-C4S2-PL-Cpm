package ast.sentences.instructions;

import exceptions.InvalidTypeException;
import exceptions.MatchingTypeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ast.Delta;
import ast.Josito;
import ast.Utils;
import ast.expressions.Expression;
import ast.preamble.Program;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;

public class Switch_Ins extends Instruction {
    HashMap<Integer, Case_Ins> mapValuesToCases;
    List<Case_Ins> clauses;
    Default_Ins default_Ins;

    public Switch_Ins(Expression cond, List<Case_Ins> clauses, Default_Ins _default, int row) {
        super(cond, null, row);
        this.clauses = clauses;
        this.default_Ins = _default;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Utils.appendIndent(str, indentation);
        str.append("switch( " + argExpression.toString() + ")\n");
        for(Case_Ins clause : clauses)
            str.append(clause.toString());
        str.append(default_Ins.toString());
        return str.toString();
    }

    public List<Boolean> caseRange(Integer ini, Integer fin) { 
    /*
    * Return a boolean list that says in the interval 
    * [ini, fin] which cases are implemented
    */ 
        mapValuesToCases  = new HashMap<>();
        for(Case_Ins clause : clauses) { // The cases values will be all different
            int key = clause.getCaseValue();
            mapValuesToCases.put(key, clause);
        }
        Set<Integer> clauseValues = mapValuesToCases.keySet();
        ini = Utils.getMinSet(clauseValues);
        fin = Utils.getMaxSet(clauseValues);
        List<Boolean> casesImplemented = new ArrayList<>();
        for(int i = 0; i <= fin; i++)
            casesImplemented.add(mapValuesToCases.containsKey(ini + i));
        return casesImplemented;
    }   

    @Override
    public void bind() {
        Program.symbolsTable.newScope();
        this.argExpression.bind();
        for(Case_Ins clause : clauses)
            clause.bind();
        default_Ins.bind();
        Program.symbolsTable.closeScope();
    }

	@Override
	public void checkType() throws Exception {
        super.checkType();
        for(Case_Ins clause : clauses)
            clause.checkType();
        default_Ins.checkType();

        Type condType;        
		try {
            // Condition check
            condType = argExpression.getType();
            if (condType == null || (condType.getKind() != Type_T.BOOL && condType.getKind() != Type_T.INT))
                throw new InvalidTypeException(String.format("Switch condition must be '%s' type or int '%s'", Type_T.BOOL.name(), Type_T.INT.name()));
            
            // Matching cases with condition check
            Type clauseType;
            for(Case_Ins clause : clauses) {
                clauseType = clause.getType();
                if(clauseType.getKind() != condType.getKind())
                    throw new MatchingTypeException("Case doesn't match the type in the Switch condition");
            }
        } catch (Exception e) {
            System.out.println(e);
            Utils.printErrorRow(row);
        }
	}

    
    @Override
    public void maxMemory(Integer c, Integer maxi) { 
        /*
         * The memory occupied by the switch will be the accumulated memory
         * of all the cases and the default
         */
        maximumMemory = 0;
        Integer curr = 0;
        for (Case_Ins _case : clauses) {
            _case.maxMemory(curr, maximumMemory);
            curr = maximumMemory;
        }
        default_Ins.maxMemory(curr, maximumMemory);
        curr = maximumMemory;
        if (c + maximumMemory > maxi)
            maxi = c + maximumMemory;
    }

    @Override
    public void computeOffset(Delta delta) {
        super.computeOffset(delta);
        for (Case_Ins c : clauses)
            c.computeOffset(delta);
        default_Ins.computeOffset(delta);
    }

/* 
    @Override
    public void generateCode(Josito jose) { 
        argExpression.generateValue(jose);

    }
    */ // TODO hay que pensarla bien

    @Override // TODO queda terminarla bien
    public void generateCode(Josito jose) { 
        
        argExpression.generateValue(jose);

    }
}
