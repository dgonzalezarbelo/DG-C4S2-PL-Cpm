package ast.sentences.instructions;

import exceptions.InvalidTypeException;
import exceptions.MatchingTypeException;
import utils.GoodBoolean;
import utils.GoodInteger;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ast.Delta;
import ast.Josito;
import ast.SymbolsTable;
import ast.expressions.Expression;
import ast.types.interfaces.Type;
import ast.types.interfaces.Type.Type_T;

public class Switch_Ins extends Instruction {
    HashMap<Integer, Integer> mapCaseValuesToPosition;
    HashMap<Integer, Case_Ins> mapValuesToCases;
    Integer min; // Min value of the cases
    Integer max; // Max value of the cases
    Integer size; // How many cases are
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

    @Override
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        for (Case_Ins c : clauses)
            c.propagateStaticVars(g, s);
        default_Ins.propagateStaticVars(g, s);
    }

    @Override
    public void bind() {
        symbolsTable.newScope();
        this.argExpression.bind();
        for(Case_Ins clause : clauses)
            clause.bind();
        default_Ins.bind();
        symbolsTable.closeScope();
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
            this.errorFlag.setValue(true);
        }
	}

    
    @Override
    public void maxMemory(GoodInteger c, GoodInteger maxi) { 
        /*
         * The memory occupied by the switch will be the accumulated memory
         * of all the cases and the default
         */
        maximumMemory.setValue(0);
        GoodInteger curr = new GoodInteger(0);
        for (Case_Ins _case : clauses) {
            _case.maxMemory(curr, maximumMemory);
            curr.setValue(maximumMemory.toInt());
        }
        default_Ins.maxMemory(curr, maximumMemory);
        curr.setValue(maximumMemory.toInt());
        if (c.toInt() + maximumMemory.toInt() > maxi.toInt())
            maxi.setValue(c.toInt() + maximumMemory.toInt());
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

    @Override
    public void generateCode(Josito jose) { 
        min = 0; max = 0; size = 0;
        caseRange();
        jose.multipleBlocks(size + 2);  // You need size and two more (default and exit switch)
        try {
            argExpression.generateValue(jose);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.printErrorRow(row);
        }
        jose.switchVar(min); // At the top of stack will be argExpression - min
        jose.jumpTable(br_table_list());
        jose.pushBreakJumpScope(size + 1);   // Update breakJumpScope pushing the switch break value
        generateCasesCode(jose);
        jose.popBreakJumpScope();   // Update breakJumpScope poping the switch break value
    }

    private void generateCasesCode(Josito jose) { 
        for(Case_Ins clause : clauses) {
            jose.endBlock();
            jose.updateBreakJumpScopeTop(jose.getBreakJumpScope() - 1); // Update switch break value because we are inside one clause
            clause.generateCode(jose);
        }
        jose.endBlock();
        jose.updateBreakJumpScopeTop(jose.getBreakJumpScope() - 1); // Update switch break value because we are inside default
        default_Ins.generateCode(jose);
        jose.endBlock();
    }

    private List<Integer> br_table_list() {
        List<Integer> ret = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if(mapCaseValuesToPosition.containsKey(i)) {
                ret.add(mapCaseValuesToPosition.get(i));
            }
            else
                ret.add(size);
        }
        ret.add(size);
        return ret;
    }

    public void caseRange() { 
        /*
        * Fill the mapCaseValuesToPosition map and gives the cases value interval 
        * [min, max] and how many clauses are (size)
        */ 
   
        mapCaseValuesToPosition  = new HashMap<>();
        int i = 0;
        for(Case_Ins clause : clauses) { // The cases values will be all different
            int key = clause.getCaseValue();
            mapCaseValuesToPosition.put(key, i);
            i++;
        }
        Set<Integer> clauseValues = mapCaseValuesToPosition.keySet();
        min = Utils.getMinSet(clauseValues);
        max = Utils.getMaxSet(clauseValues);
        size = clauseValues.size();
    }

    //---------------------------------------------------------------------------------------

    private void generateCasesCode1(Josito jose) { //TODO antigua funcion del switch para el generate code [preguntar a Javi]
        for (int i = min; i < max; i++) {
            if(mapValuesToCases.containsKey(i)) {
                jose.endBlock();
                jose.updateBreakJumpScopeTop(jose.getBreakJumpScope() - 1); // Update switch break value because we are inside one clause
                mapValuesToCases.get(i).generateCode(jose);
            }
        }
        jose.endBlock();
        jose.updateBreakJumpScopeTop(jose.getBreakJumpScope() - 1); // Update switch break value because we are inside default
        default_Ins.generateCode(jose);
        jose.endBlock();
    }

    private List<Integer> br_table_list1() { //TODO antigua funcion del switch para el generate code [preguntar a Javi]
        List<Integer> ret = new ArrayList<>();
        int current = 0; // Current b_table label
        for (int i = min; i < max; i++) {
            if(mapValuesToCases.containsKey(i)) {
                ret.add(current);
                current++;
            }
            else
                ret.add(size);
        }
        ret.add(size);
        return ret;
    }

    public void caseRange1() { //TODO antigua funcion del switch àra el generate code [preguntar a Javi]
        /*
        * Fill the mapValuesToCases map and gives the cases value interval 
        * [min, max] and how many clauses are (size)
        */ 
   
        mapValuesToCases  = new HashMap<>();
        for(Case_Ins clause : clauses) { // The cases values will be all different
            int key = clause.getCaseValue();
            mapValuesToCases.put(key, clause);
        }
        Set<Integer> clauseValues = mapValuesToCases.keySet();
        min = Utils.getMinSet(clauseValues);
        max = Utils.getMaxSet(clauseValues);
        size = clauseValues.size();
    } 
}
