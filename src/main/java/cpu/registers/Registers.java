package cpu.registers;

import utils.BitUtils;

public class Registers {
    private static final int WORD_MASK = 0xFFFF;

    private Register a;  // the accumulator!
    private Register b;
    private Register c;
    private Register d;
    private Register e;
    private Register h;
    private Register l;

    public int getBC() {
        return getPair(b, c);
    }

    public int getDE() {
        return getPair(d, e);
    }

    public int getHL() {
        return getPair(h, l);
    }

    private int getPair(Register low, Register high) {
        int value = ((low.getValue() << 8) | high.getValue());
        assert((value &= WORD_MASK) == value);
        return value;
    }
}
