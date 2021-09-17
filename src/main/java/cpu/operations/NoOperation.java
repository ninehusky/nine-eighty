package cpu.operations;

import cpu.flags.Flags;
import cpu.registers.ProgramCounter;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

public class NoOperation implements Operation {
    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        pc.increment();
        return 1;
    }

    @Override
    public String toString() {
        return "NOP";
    }
}
