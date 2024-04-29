package ast.sentences;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.types.Type;

public class Block implements ASTNode {
    public List<Sentence> ins;

    public Block() {
        this.ins = new ArrayList<>();
    }

    public Block(List<Sentence> ins) {
        this();
        for (Sentence i : ins) {
            this.ins.add(i);
        }
    }

    public Block add_instruction(Sentence i) {
        this.ins.add(i);
        return this;
    }

    public boolean empty() {
        return this.ins == null || this.ins.isEmpty();
    }

    public void propagateIndentation(int indent) {
        if (this.ins == null) return;
        for (Sentence s : this.ins)
            if (s != null) s.propagateIndentation(indent);
    }

    public String toString() {
        if (this.ins == null) return "";
        StringBuilder str = new StringBuilder();
        for (Sentence i : ins)
            str.append(i.toString());
        return str.toString();
    }

    @Override
    public void bind() {
        for (Sentence s : ins) {
            s.bind();
        }
    }

    @Override
	public Type checkType() throws Exception {
        for (Sentence s : ins)
            s.checkType();
        return null;
    }
}
