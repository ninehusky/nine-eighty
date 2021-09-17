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
 * Moves data from one register to the other. Note that operations like MOV X, H should be executed using the
 * MovRegisterToMemory class.
 */
public class MovRegisterToRegister implements Operation {
    private Register dst;
    private Register src;

    public MovRegisterToRegister(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        int value = dst.getValue();
        src.setValue(value);

        pc.increment();
        return 1;
    }

    @Override
    public String toString() {
        return "MOV " + dst + ", " + src;
    }
}
