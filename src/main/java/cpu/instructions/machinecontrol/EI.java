package cpu.instructions.machinecontrol;

import cpu.instructions.Instruction;

public class EI {
    public static Instruction enableInterrupts() {
        return (regs, f, bus) -> {
            regs.setInterruptsEnabled(true);
            regs.incrementProgramCounter();
            return 1;
        };
    }
}
