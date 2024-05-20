package ast.sentences;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.Delta;
import ast.Indentable;
import ast.Josito;
import ast.SymbolsTable;
import utils.GoodBoolean;
import utils.GoodInteger;

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
    public void propagateStaticVars(GoodBoolean g, SymbolsTable s) {
        super.propagateStaticVars(g, s);
        for (Sentence sen : ins)
            sen.propagateStaticVars(g, s);
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
    public void maxMemory(GoodInteger c, GoodInteger maxi) { 
        /* 
         * The parameters are refering the current memory and the maximun memory of the scope that call me
         * and internally im creating new ones to control my local memory limits. Thus, although i can have
         * another block below me, he will never change my curr value, because he is as careful as me.
        */
        maximumMemory.setValue(0);
        GoodInteger curr = new GoodInteger(0);
        for (Sentence s : ins) { 
            s.maxMemory(curr, maximumMemory); // Only the declarations will change the curr value
            if (curr.toInt() > maximumMemory.toInt())
                maximumMemory.setValue(curr.toInt());
        }
        if (c.toInt() + maximumMemory.toInt() > maxi.toInt())
            maxi.setValue(c.toInt() + maximumMemory.toInt());
    }

    @Override
    public void computeOffset(Delta delta) {
        delta.pushScope(delta.getCurrentDelta()); // To have in consideration the DL and reference in the local memory of the function
        for (Sentence s : ins)
            s.computeOffset(delta);
        delta.popScope();
    }

    @Override
    public void generateCode(Josito jose) { 
        for(Sentence instruction : ins)
            instruction.generateCode(jose);
    }
}
