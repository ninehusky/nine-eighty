package cpu.instructions.machinecontrol;

import cpu.instructions.Instruction;

public class NOP {
    public static Instruction noOperation() {
        return (r, f, bus) -> {
            r.incrementProgramCounter();
            return 1;
        };
    }
}
