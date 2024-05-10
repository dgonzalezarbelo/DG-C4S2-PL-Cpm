package utils;

public class GoodInteger {
    private int value;

    public GoodInteger(int val) {
        this.value = val;
    }

    public int setValue(int value) {
        return this.value = value;
    }

    public int toInt() {
        return this.value;
    }
}
