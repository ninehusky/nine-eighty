package cpu.instructions.machinecontrol;

import cpu.CPUState;
import cpu.instructions.Instruction;

public class HLT {
    public static Instruction haltInstruction() {
        return (regs, f, bus) -> {
            regs.setCPUState(CPUState.HALTED);
            regs.incrementProgramCounter();
            return 1;
        };
    }
}
