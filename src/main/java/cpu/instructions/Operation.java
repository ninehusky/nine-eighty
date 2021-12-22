package cpu.instructions;

import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;

/**
 * This represents a single instruction of the 8080.
 */
public class Operation {
    private String label;
    private Instruction instruction;

    /**
     * Constructs an Instruction.
     * @param label - the label (e.g., "MOV A, B") corresponding to the instruction
     * @param instruction - the instruction itself, i.e., the code to execute when the operation is ran.
     */
    public Operation(String label, Instruction instruction) {
        this.label = label;
        this.instruction = instruction;
    }

    /**
     * Alters the given components of a CPU state by executing the instruction
     * corresponding to this.
     * @param regs - the registers of the CPU
     * @param flags - the flags of the CPU
     * @param bus - the memory of the CPU
     */
    public void execute(Registers regs, Flags flags, MemoryBus bus) {
        instruction.execute(regs, flags, bus);
    }

    @Override
    public String toString() {
        return label;
    }
}
