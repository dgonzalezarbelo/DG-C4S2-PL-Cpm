package ast.sentences;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Indentable;
import ast.Josito;

public class Block extends ASTNode implements Indentable {
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

    @Override
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
        for (Sentence s : ins)
            s.bind();
    }

    @Override
	public void checkType() throws Exception {
        for (Sentence s : ins)
            s.checkType();
    }

    @Override
    public void maxMemory(Integer c, Integer maxi) { 
        /* 
         * The parameters are refering the current memory and the maximun memory of the scope that call me
         * and internally im creating new ones to control my local memory limits. Thus, although i can have
         * another block below me, he will never change my curr value, because he is as careful as me.
        */
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

    @Override
    public void computeOffset(Delta delta) {
        for (Sentence s : ins)
            s.computeOffset(delta);
    }

    @Override
    public void generateCode(Josito jose) { 
        for(Sentence instruction : ins)
            instruction.generateCode(jose);
    }
}
