package ast;

import java.util.Stack;

public class Delta {
    private Stack<Integer> offsets;

    public Delta() {
        this.offsets = new Stack<>();
    }

    public void pushScope() {
        offsets.push(0);
    }

    public void popScope() {
        offsets.pop();
    }

    public int getAndUpdateOffset(Integer size) {
        Integer top = offsets.peek();
        int ret_value = top.intValue();
        top += size;
        return ret_value;
    }
}
