package ast.sentences;

import java.util.ArrayList;
import java.util.List;

public class Block {
    public List<Sentence> ins;

    public Block() {
        this.ins = new ArrayList<>();
    }

    public Block(List<Sentence> ins) {
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
}
