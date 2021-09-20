package cpu.operations.datatransfer;

import cpu.CPUUtils;
import cpu.flags.Flags;
import cpu.operations.Operation;
import cpu.registers.ProgramCounter;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

/**
 * Represents the instruction LXI, SP.
 * Other LXI instructions should not be represented with this class.
 *
 * Flags affected: None
 */
public class LoadStackPointerImmediate implements Operation {
    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int address = r.getHL();
        int word = CPUUtils.getNextWord(pc, memoryBus);

        // the stack pointer is stored in little endian
        int high = word & 0xFF;
        int low = word >>> 8;

        int finalValue = (high << 8) | low;

        sp.setValue(finalValue);

        pc.increment(3);
        return 3;
    }
}
