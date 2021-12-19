package cpu.registers;

/**
 * Represents the seven 8-bit registers that accompany the 8080, alongside the CPU's
 * 16-bit stack pointer and program counter.
 */
public class Registers {
    public enum Register {
        A,
        B,
        C,
        D,
        F,
        H,
        L
    };

    private int a;
    private int b;
    private int c;
    private int d;
    private int f;
    private int h;
    private int l;

    private Pointer sp;
    private Pointer pc;

    public Registers() {
        sp = new Pointer();
        pc = new Pointer();
    }

    public Pointer getStackPointer() {
        return sp;
    }

    public Pointer getProgramCounter() {
        return pc;
    }

    /**
     * Returns the value to the given 8-bit register.
     * @param r - the register whose value is returned
     * @return the value of the 8-bit register
     */
    public int read(Register r) {
        switch (r) {
            case A:
                return a;
            case B:
                return b;
            case C:
                return c;
            case D:
                return d;
            case F:
                return f;
            case H:
                return h;
            default:
                return l;
        }
    }

    /**
     * Writes the given value to the given 8-bit register.
     * @throws IllegalArgumentException if val > 255 (i.e., higher than 8-bit value)
     * @param r - the register whose value ot overwrite
     * @param val - the value to be written
     */
    public void write(Register r, int val) {
        if ((val & ~0xFF) != 0) {
            throw new IllegalArgumentException("Cannot write value " + Integer.toHexString(val) + " to register!");
        }
        switch (r) {
            case A:
                a = val;
            case B:
                b = val;
            case C:
                c = val;
            case D:
                d = val;
            case F:
                f = val;
            case H:
                h = val;
            default:
                l = val;
        }
    }
}
