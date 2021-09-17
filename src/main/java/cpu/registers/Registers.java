package cpu.registers;

import utils.BitUtils;

public class Registers {
    private static final int WORD_MASK = 0xFFFF;

    private final Register a;  // the accumulator!
    private final Register b;
    private final Register c;
    private final Register d;
    private final Register e;
    private final Register h;
    private final Register l;

    public Registers() {
        a = new Register("A");
        b = new Register("B");
        c = new Register("C");
        d = new Register("D");
        e = new Register("E");
        h = new Register("H");
        l = new Register("L");
    }

    public Register getA() {
        return a;
    }

    public Register getB() {
        return b;
    }

    public Register getC() {
        return c;
    }

    public Register getD() {
        return d;
    }

    public Register getE() {
        return e;
    }

    public Register getH() {
        return h;
    }

    public Register getL() {
        return l;
    }

    public int getBC() {
        return getPair(b, c);
    }

    public void setBC(int value) {
        setPair(b, c, value);
    }

    public int getDE() {
        return getPair(d, e);
    }

    public void setDE(int value) {
        setPair(d, e, value);
    }

    public int getHL() {
        return getPair(h, l);
    }

    public void setHL(int value) {
        setPair(h, l, value);
    }

    private int getPair(Register low, Register high) {
        int value = ((low.getValue() << 8) | high.getValue());
        assert((value &= WORD_MASK) == value);
        return value;
    }

    private void setPair(Register low, Register high, int value) {
        int highValue = (value & 0xFF00) >> 8;
        int lowValue = value & 0xFF;
        high.setValue(highValue);
        low.setValue(lowValue);
    }
}
