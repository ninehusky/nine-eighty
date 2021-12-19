package mmu;

/**
 * Represents random-access memory, a buffer of memory that can be read from or written to.
 */
public class RAM implements AddressSpace {
    protected int[] buffer;

    /**
     * Constructs a new RAM
     *
     * @param capacity - the capacity of the memory, i.e., 1 higher than the maximum address of the address space.
     */
    public RAM(int capacity) {
        this.buffer = new int[capacity];
    }

    @Override
    public int read(int address) {
        checkAddress(address);
        return buffer[address];
    }

    @Override
    public void write(int address, int value) {
        checkAddress(address);
        buffer[address] = value;
    }

    private void checkAddress(int address) {
        if (address >= buffer.length) {
            throw new IllegalArgumentException("Address " + Integer.toHexString(address) + " is out of bounds!");
        }
    }
}
