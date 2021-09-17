package memory.ram;

import memory.AddressSpace;
import utils.BitUtils;

public class RAM extends AddressSpace {
    public RAM(int size) {
        memory = new int[size];
    }

    @Override
    public void setByte(int address, int value) {
        BitUtils.checkByte(value);
        checkAddress(address);
        memory[address] = value;
    }
}
