package memory;

import utils.BitUtils;

public abstract class AddressSpace {
    protected int[] memory;

    public int getByte(int address) {
        checkAddress(address);
        return memory[address];
    }

    public abstract void setByte(int address, int value);

    protected void checkAddress(int address) {
        if (address < 0 || address > memory.length) {
            throw new IllegalArgumentException("Cannot access memory at index " + BitUtils.hexWord(address));
        }
    }
}
