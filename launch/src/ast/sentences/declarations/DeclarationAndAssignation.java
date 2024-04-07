package ast.sentences.declarations;

import ast.Expression;
import ast.sentences.Sentence;
import ast.sentences.instructions.Assignation_Ins;
import ast.types.Id_Value;

public class DeclarationAndAssignation extends Sentence {
    private Declaration d;
    private Assignation_Ins a;

    public DeclarationAndAssignation(Declaration d, Expression a) {
        this.d = d;
        this.a = new Assignation_Ins(new Id_Value(d.getId().toString()), a);
    }

    public String toString() {return "Declaration: " + this.d.toString() + "\n" + "With value " + a.toString() ;};
}