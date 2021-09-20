package cpu.operations.datatransfer;

import cpu.flags.Flags;
import cpu.operations.Operation;
import cpu.registers.ProgramCounter;
import cpu.registers.Register;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

/**
 * Represents a STAX operation.
 * Flags set: none
 */
public class StoreAInRegisterPair implements Operation {
    private Register first;
    private Register second;

    public StoreAInRegisterPair(Register first, Register second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int address = (first.getValue() << 8) | second.getValue();
        memoryBus.setByte(address, r.getA().getValue());
        pc.increment();
        return 2;
    }
}
