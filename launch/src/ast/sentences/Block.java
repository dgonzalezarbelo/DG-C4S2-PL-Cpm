package ast.sentences;

import java.util.List;

public class Block {
    public List<Sentence> ins;

    public Block(List<Sentence> ins) {
        for (Sentence i : ins) {
            this.ins.add(i);
        }
    }

    public Block add_instruction(Sentence i) {
        this.ins.add(i);
        return this;
    }
}
