package ast.sentences;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.types.Type;

public class Block implements ASTNode {
    public List<Sentence> ins;
    private Integer maximumMemory;

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

    @Override
    public void maxMemory(Integer c, Integer maxi) {
        maximumMemory = 0;
        Integer curr = 0;
        for (Sentence s : ins) {
            s.maxMemory(curr, maximumMemory); // Only the declarations will change the curr value
            if (curr > maximumMemory)
                maximumMemory = curr;
        }
        if (c + maximumMemory > maxi)
            maxi = c + maximumMemory;
    }
}

/* // 20

declaraciones... //10 c = max (c apunta a curr del bloque de antes)
{

} // 10 curr = max

{ // curr = 10

} // 5
*/
