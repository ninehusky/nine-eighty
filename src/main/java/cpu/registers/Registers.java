package cpu.registers;

import cpu.CPUState;

/**
 * Represents the seven 8-bit registers that accompany the 8080, alongside the CPU's
 * 16-bit stack pointer and program counter. Also manages the state of the 8080.
 */
public class Registers {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int h;
    private int l;

    private CPUState state;

    private final Pointer sp;
    private final Pointer pc;

    public Registers() {
        sp = new Pointer();
        pc = new Pointer();
        state = CPUState.RUNNING;
    }

    /**
     * Returns the CPU's state with the given state.
     * @return the state of the CPU
     */
    public CPUState getCPUState() {
        return this.state;
    }

    /**
     * Replaces the CPU's state with the given state.
     * @param state - the state to assign to the CPU
     */
    public void setCPUState(CPUState state) {
        this.state = state;
    }

    public Pointer getStackPointer() {
        return sp;
    }

    public Pointer getProgramCounter() {
        return pc;
    }

    /**
     * Increments the program counter by n.
     * @param n - the amount to increment
     * @throws IllegalArgumentException if incrementing the program counter by the given amount results in overflow.
     */
    public void incrementProgramCounter(int n) {
        pc.write(pc.read() + n);
    }

    /**
     * Increments the program counter by 1.
     * Equivalent to <code>incrementProgramCounter(1)code>.
     */
    public void incrementProgramCounter() {
        this.incrementProgramCounter(1);
    }

    /**
     * Returns the 8-bit value to the given register.
     * @param r - the register whose value is returned
     * @return the value of the 8-bit register
     */
    public int read(Register r) {
        return switch (r) {
            case A -> a;
            case B -> b;
            case C -> c;
            case D -> d;
            case E -> e;
            case H -> h;
            default -> l;
        };
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
        switch (pair) {
            case BC -> {
                highBits = b;
                lowBits = c;
            }
            case DE -> {
                highBits = d;
                lowBits = e;
            }
            default -> {
                highBits = h;
                lowBits = l;
            }
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
        switch (pair) {
            case BC -> {
                b = highBits;
                c = lowBits;
            }
            case DE -> {
                d = highBits;
                e = lowBits;
            }
            default -> {
                h = highBits;
                l = lowBits;
            }
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
            case A -> a = val;
            case B -> b = val;
            case C -> c = val;
            case D -> d = val;
            case E -> e = val;
            case H -> h = val;
            default -> l = val;
        }
    }
}
