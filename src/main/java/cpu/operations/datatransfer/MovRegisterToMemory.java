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
 * Moves data from a register the address in memory pointed to by HL.
 * Note that intra-register transfers should be executed using the
 * MovRegisterToRegister class.
 */
public class MovRegisterToMemory implements Operation {
    private Register src;

    public MovRegisterToMemory(Register src) {
        this.src = src;
    }

    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int address = r.getHL();

        memoryBus.setByte(address, src.getValue());

        pc.increment();
        return 1;
    }

    @Override
    public String toString() {
        return "MOV " + src + " M";
    }
}
