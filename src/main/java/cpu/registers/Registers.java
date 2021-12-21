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
        E,
        H,
        L
    };

    /**
     * Represents the pairs that are often accessed in 8080 instructions.
     */
    public enum RegisterPair {
        BC,
        DE,
        HL
    };

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
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
     * Returns the 8-bit value to the given register.
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
            case E:
                return e;
            case H:
                return h;
            default:
                return l;
        }
    }

    /**
     * Returns the 16-bit value corresponding to the given register pair.
     * For example, if register B stores 0xAB, and register C stores 0xD1,
     * then <code>readPair(RegisterPair.BC)</code> will return 0xABD1.
     * @param pair - the pair to read
     * @return - the value corresponding to that pair
     */
    public int readPair(RegisterPair pair) {
        int highBits;  // most significant bits of value
        int lowBits;   // least significant bits of value
        switch(pair) {
            case BC:
                highBits = b;
                lowBits = c;
                break;
            case DE:
                highBits = d;
                lowBits = e;
                break;
            default:
                highBits = h;
                lowBits = l;
                break;
        }
        int value = (highBits << 8) | lowBits;
        // ensure that we didn't accidentally create an impossible value
        assert((value & 0xFFFF) == value);
        return value;
    }

    /**
     * Sets the given register pair equal to the given value.
     * For example, <code>writePair(RegisterPair.BC, 0xABCD)</code> will set
     * B equal to the value's most significant bits, i.e., 0xAB, and C equal to
     * the value's least significant bits, 0xCD.
     * @param pair - the pair to change
     * @param value - the value to put in the pair
     */
    public void writePair(RegisterPair pair, int value) {
        if ((value & 0xFFFF) != value) {
            throw new IllegalArgumentException("Cannot store value " + Integer.toHexString(value) +
                                               " in register pair!");
        }
        int highBits = (value >>> 8);  // most significant bits of value
        int lowBits = (value & 0xFF);   // least significant bits of value
        switch(pair) {
            case BC:
                b = highBits;
                c = lowBits;
                break;
            case DE:
                d = highBits;
                System.out.println(Integer.toHexString(d));
                e = lowBits;
                break;
            default:
                h = highBits;
                l = lowBits;
                break;
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
                break;
            case B:
                b = val;
                break;
            case C:
                c = val;
                break;
            case D:
                d = val;
                break;
            case E:
                e = val;
                break;
            case H:
                h = val;
                break;
            default:
                l = val;
                break;
        }
    }
}
