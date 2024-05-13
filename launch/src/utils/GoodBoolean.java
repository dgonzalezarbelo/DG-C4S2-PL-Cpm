package utils;

public class GoodBoolean {
    private boolean value;

    public GoodBoolean(boolean val) {
        this.value = val;
    }

    public boolean setValue(boolean value) {
        return this.value = value;
    }

    public boolean toBool() {
        return this.value;
    }
}
