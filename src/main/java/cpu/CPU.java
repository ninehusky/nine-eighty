package cpu;

import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;

/**
 * Represents an Intel 8080.
 */
public class CPU {
    private Registers registers;  // seven 8-bit registers
    private Flags flags;          // condition bits
    private MemoryBus bus;
}
