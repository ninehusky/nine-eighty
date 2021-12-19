package mmu;

/**
 * Represents a Memory Bus, which manages read/write operations across the address space of the
 * Space Invaders arcade cabinet.
 */
public class MemoryBus implements AddressSpace {
    public static final int ROM_START_ADDRESS  = 0x0000;
    public static final int RAM_START_ADDRESS  = 0x2000;
    public static final int VRAM_START_ADDRESS = 0x2400;

    private static final int ROM_SIZE_IN_BYTES = 8192;
    private static final int RAM_SIZE_IN_BYTES = 1024;
    private static final int VRAM_SIZE_IN_BYTES = 7168;

    private static final int MAX_ADDRESS = 0x3FFF;

    private final ROM rom;
    private final RAM ram;
    private final RAM vram;

    public MemoryBus(byte[] romBuffer) {
        assert(romBuffer.length == ROM_SIZE_IN_BYTES);
        this.rom = new ROM(romBuffer);
        this.ram = new RAM(RAM_SIZE_IN_BYTES);
        this.vram = new RAM(VRAM_SIZE_IN_BYTES);
    }

    @Override
    public int read(int address) {
        address &= MAX_ADDRESS;
        if (address < RAM_START_ADDRESS) {
            return rom.read(address - ROM_START_ADDRESS);
        } else if (address < VRAM_START_ADDRESS) {
            return ram.read(address - RAM_START_ADDRESS);
        } else {
            return vram.read(address - VRAM_START_ADDRESS);
        }
    }

    @Override
    public void write(int address, int value) {
        address &= MAX_ADDRESS;
        if (address < RAM_START_ADDRESS) {
            rom.write(address - ROM_START_ADDRESS, value);
        } else if (address < VRAM_START_ADDRESS) {
            ram.write(address - RAM_START_ADDRESS, value);
        } else {
            vram.write(address - VRAM_START_ADDRESS, value);
        }
    }
}
