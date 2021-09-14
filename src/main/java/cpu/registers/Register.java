package cpu.registers;

import utils.BitUtils;

public class Register {
    private static final int REGISTER_MASK = 0xFF;

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        checkValue(value);
        this.value = value;
    }

    private void checkValue(int value) {
        if ((value & 0xFF) != value) {
            throw new IllegalStateException("8-bit register cannot contain value " + BitUtils.hexWord(value));
        }
    }
}
