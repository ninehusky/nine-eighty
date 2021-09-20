package cpu.operations.datatransfer;

import cpu.CPUUtils;
import cpu.flags.Flags;
import cpu.operations.Operation;
import cpu.registers.ProgramCounter;
import cpu.registers.Register;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

/**
 * Represents LXI, X operation where X represents a register pair.
 * Note that register pairs should be either BC, DE, or HL.
 *
 * LXI, SP operations should be executed with a LoadStackPointerImmediate operation.
 *
 * Flags set: None
 */
public class LoadRegisterPairImmediate implements Operation {
    private Register first;
    private Register second;

    public LoadRegisterPairImmediate(Register first, Register second) {
        assert(first != second);
        this.first = first;
        this.second = second;
    }

    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int word = CPUUtils.getNextWord(pc, memoryBus);

        int low = word & 0xFF;
        int high = word >>> 8;

        first.setValue(low);
        second.setValue(high);

        pc.increment(3);
        return 3;
    }
}
