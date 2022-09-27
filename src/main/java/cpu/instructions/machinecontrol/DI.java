package cpu.instructions.machinecontrol;

import cpu.instructions.Instruction;

public class DI {
    public static Instruction disableInterrupts() {
        return (regs, f, bus) -> {
            regs.setInterruptsEnabled(false);
            regs.incrementProgramCounter();
            return 1;
        };
    }
}
