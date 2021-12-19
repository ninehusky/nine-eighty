package mmu;

/**
 * Represents an address space, i.e., a buffer of memory that can be read from and written to.
 */
public interface AddressSpace {
    /**
     * Returns the byte located at the given address.
     * @param address - the address to find the byte
     * @throws IllegalArgumentException if the address is invalid, i.e., it is out of bounds
     * @return the byte of the address space located at the address
     */
    public int read(int address);

    /**
     * Returns the byte located at the given address.
     * @param address - the address to find the byte
     * @throws IllegalArgumentException if the address is invalid, i.e., it is out of bounds
     * @return the byte of the address space located at the address
     */
    public void write(int address, int value);
}