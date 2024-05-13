package ast;

import java.util.Stack;

public class Delta {
    private Stack<Integer> offsets;

    public Delta() {
        this.offsets = new Stack<>();
    }

    public void pushScope(int inicialDelta) {
        offsets.push(inicialDelta);
    }

    public void popScope() {
        offsets.pop();
    }

    public int getAndUpdateOffset(Integer size) {
        Integer top = offsets.peek();
        offsets.pop();
        int ret_value = top.intValue();
        top += size;
        offsets.push(top);
        return ret_value;
    }
}
