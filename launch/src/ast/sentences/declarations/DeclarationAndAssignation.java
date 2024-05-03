package ast.sentences.declarations;

import ast.expressions.Expression;
import ast.expressions.values.VariableID;
import ast.sentences.Sentence;
import ast.sentences.instructions.Assignation_Ins;
import ast.types.Type;

public class DeclarationAndAssignation extends Sentence {
    private Declaration d;
    private Assignation_Ins a;

    public DeclarationAndAssignation(Declaration d, Expression a, int row) {
        this.d = d;
        this.a = new Assignation_Ins(new VariableID(d.getId().toString(), row), a, row);
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

    @Override
    public Type checkType() throws Exception {
        Type t = this.d.checkType();
        this.a.checkType();
        return t;
    }
}