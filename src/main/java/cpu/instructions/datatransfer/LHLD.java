package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;

public class LHLD {
    public static Instruction loadHLImmediate() {
        return (regs, f, bus) -> {
            int lData = bus.read(regs.getProgramCounter().read() + 1);
            int hData = bus.read(regs.getProgramCounter().read() + 2);

            regs.write(Register.L, lData);
            regs.write(Register.H, hData);

            regs.incrementProgramCounter(3);
            return 5;
        };
    }
}
