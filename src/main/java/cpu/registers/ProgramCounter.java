package cpu.registers;

public class ProgramCounter {
    private static final int STARTING_ADDRESS = 0x100;

    private int value;

    public ProgramCounter() {
        this.value = 0x0; // TODO: replace with STARTING_ADDRESS
    }

    public int getValue() {
        return value;
    }

    /**
     * Increments the program counter.
     * This is equivalent to a call of increment(1).
     */
    public void increment() {
        this.increment(1);
    }

    /**
     * Increments the program counter by n.
     * @param n - number of bytes to move the program counter forward
     */
    public void increment(int n) {
        value += n;
    }
}
