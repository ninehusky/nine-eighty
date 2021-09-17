package memory;

import memory.ram.RAM;
import memory.ram.VRAM;
import memory.rom.ROM;
import utils.BitUtils;

import java.io.File;

public class MemoryBus {
    public static final int RAM_START_ADDRESS = 0x2000;
    public static final int VRAM_START_ADDRESS = 0x2400;
    public static final int MEMORY_ACCESS_MASK = 0x3FFF;  // 0b0011_1111_1111_1111

    private static final int ROM_SIZE_IN_BYTES = 8192;
    private static final int RAM_SIZE_IN_BYTES = 1024;
    private static final int VRAM_SIZE_IN_BYTES = 7168;  // weird TODO: potentially write this as HEIGHT * WIDTH, expose specs here instead of vram

    private ROM rom;
    private RAM ram;
    private VRAM vram;

    public MemoryBus() {
        rom = new ROM(ROM_SIZE_IN_BYTES, new File("src/test/resources/debug.rom"));
        ram = new RAM(RAM_SIZE_IN_BYTES);
        vram = new VRAM(VRAM_SIZE_IN_BYTES);
    }

    public int getByte(int address) {
        address &= MEMORY_ACCESS_MASK;
        AddressSpace space = getAddressSpace(address);
        if (space == ram) {
            address -= RAM_START_ADDRESS;
        } else if (space == vram) {
            address -= VRAM_START_ADDRESS;
        }
        return space.getByte(address);
    }

    public void setByte(int address, int value) {
        BitUtils.checkByte(value);
        address &= MEMORY_ACCESS_MASK;
        AddressSpace space = getAddressSpace(address);
        if (space == ram) {
            address -= RAM_START_ADDRESS;
        } else if (space == vram) {
            address -= VRAM_START_ADDRESS;
        }
        space.setByte(address, value);
    }

    private AddressSpace getAddressSpace(int address) {
        if (address < RAM_START_ADDRESS) {
            return this.rom;
        } else if (address < VRAM_START_ADDRESS) {
            return this.ram;
        }
        return this.vram;
    }
}
