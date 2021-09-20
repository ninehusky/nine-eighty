package cpu.operations.datatransfer;

import cpu.flags.Flags;
import cpu.operations.Operation;
import cpu.registers.ProgramCounter;
import cpu.registers.Register;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

/**
 * Represents one variant of the MOV command.
 *
 * Moves data from a the address in memory pointed to by HL to a destination register.
 * Note that alternate MOV variants should be executed using different Mov classes.
 *
 * Flags set: None
 */
public class MovMemoryToRegister implements Operation {
    private Register dest;

    public MovMemoryToRegister(Register dest) {
        this.dest = dest;
    }

    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int address = r.getHL();
        int value = memoryBus.getByte(address);
        dest.setValue(value);

        pc.increment();
        return 1;
    }

    @Override
    public String toString() {
        return "MOV M, " + dest;
    }
}
