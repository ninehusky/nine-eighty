package cpu.instructions;

import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;

/**
 * Represents a single instruction, i.e., an operation that can change the internals of the CPU/memory
 * that corresponds to one or more opcodes.
 */
@FunctionalInterface
public interface Instruction {
    // Here we go
    int execute(Registers r, Flags f, MemoryBus bus);
}