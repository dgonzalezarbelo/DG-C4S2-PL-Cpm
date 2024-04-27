package ast.sentences.declarations;

import ast.Expression;
import ast.preamble.Program;
import ast.sentences.Sentence;
import ast.sentences.instructions.Assignation_Ins;
import ast.types.Id_Value;
import exceptions.DuplicateDefinitionException;

public class DeclarationAndAssignation extends Sentence {
    private Declaration d;
    private Assignation_Ins a;

    public DeclarationAndAssignation(Declaration d, Expression a) {
        this.d = d;
        this.a = new Assignation_Ins(new Id_Value(d.getId().toString()), a);
    }

    @Override
    public void propagateIndentation(int indent) {
        this.indentation = indent;
        d.propagateIndentation(indent);
        a.propagateIndentation(indent);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.d.toString());
        str.append(a.toString());
        return str.toString();
    }

    @Override
	public void bind() {
        d.bind();
        a.bind();
	}
}