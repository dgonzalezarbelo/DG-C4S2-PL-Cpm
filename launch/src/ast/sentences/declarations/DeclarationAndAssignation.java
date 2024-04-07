package ast.sentences.declarations;

import ast.Expression;
import ast.sentences.Sentence;
import ast.sentences.instructions.Assignation_Ins;

public class DeclarationAndAssignation extends Sentence {
    private Declaration d;
    private Assignation_Ins a;
    // private Boolean isReference; 
    // TODO

    public DeclarationAndAssignation(Declaration d, Expression a) {
        this.d = d;
        this.a = new Assignation_Ins(d.getId(), a);
    }

    public String toString() {return "Declaration: " + this.d.toString() + "\n" + "With value " + a.toString() ;};
}