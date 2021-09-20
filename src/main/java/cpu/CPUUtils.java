package cpu;

import cpu.registers.ProgramCounter;
import memory.MemoryBus;

public final class CPUUtils {
    /**
     * Returns the word adjacent to the program counter in memory.
     * @param pc
     * @param bus
     * @return the word next to the program counter in memory
     */
    public static int getNextWord(ProgramCounter pc, MemoryBus bus) {
        int high = bus.getByte(pc.getValue());
        int low = bus.getByte(pc.getValue() + 1);

        int value = (low << 8) | high;
        return value;
    }
}
