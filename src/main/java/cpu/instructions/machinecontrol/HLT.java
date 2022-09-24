package cpu.instructions.machinecontrol;

import cpu.CPU;
import cpu.CPUState;
import cpu.flags.Flags;
import cpu.instructions.Instruction;
import cpu.registers.Registers;
import mmu.MemoryBus;

public class HLT {
    public static Instruction haltInstruction(CPU cpu) {
        return (regs, f, bus) -> {
            cpu.setState(CPUState.STOPPED);
            regs.incrementProgramCounter();
            return 1;
        };
    }
}
