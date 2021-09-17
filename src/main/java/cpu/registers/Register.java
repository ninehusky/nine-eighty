package cpu.registers;

import utils.BitUtils;

public class Register {
    private static final int REGISTER_MASK = 0xFF;

    private final String label;
    private int value;

    public Register(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        checkValue(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }

    private void checkValue(int value) {
        if ((value & 0xFF) != value) {
            throw new IllegalStateException("8-bit register cannot contain value " + BitUtils.hexWord(value));
        }
    }
}
