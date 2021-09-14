package memory;

import utils.BitUtils;

public abstract class Memory {
    protected int size;

    public abstract int getByte(int address);
    public abstract void setByte(int address, int value);

    public void checkAddress(int address) {
        if (address < 0 || address > size) {
            throw new IllegalArgumentException("Cannot access memory at index " + BitUtils.hexWord(address));
        }
    }
}
