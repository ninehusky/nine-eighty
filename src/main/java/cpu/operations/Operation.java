package cpu.operations;

import cpu.flags.Flags;
import cpu.registers.ProgramCounter;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

/**
 * Class representing a single instruction to be executed by the 8080.
 */

@FunctionalInterface
public interface Operation {
    /**
     * Performs the operation, potentially altering one or more of the given parameters.
     * @param r
     * @param f
     * @param memoryBus
     * @param pc
     * @param sp
     * @return the number of cycles executed by the CPU
     */
    public int execute(Registers r, Flags f, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp);

    @Override
    public String toString();
}
