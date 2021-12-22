package cpu.registers;

/**
 * Represents a 16-bit register pointing to some address in the 8080's address space.
 */
public class Pointer {
    private int value;

    /**
     * Writes the given value to the pointer.
     * @param value - the value to be written to the pointer.
     * @throws IllegalArgumentException if the given value is larger than 0xFFFF
     */
    public void write(int value) {
        if ((value & 0xFFFF) != value) {
            throw new IllegalArgumentException("Cannot write value " + Integer.toHexString(value) + " to pointer.");
        }
        this.value = value;
    }

    /**
     * Returns the current value of the pointer.
     * @return - the value of the pointer
     */
    public int read() {
        return this.value;
    }
}
