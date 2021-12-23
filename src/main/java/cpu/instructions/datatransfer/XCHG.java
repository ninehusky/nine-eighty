package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.RegisterPair;

/**
 * The bits of data held in the HL registers are exchanged with the bits of data held in the DE registers.
 */
public class XCHG {
    public static Instruction exchangeData() {
        return (regs, f, bus) -> {
            int hlData = regs.readPair(RegisterPair.HL);
            int deData = regs.readPair(RegisterPair.DE);

            regs.writePair(RegisterPair.HL, deData);
            regs.writePair(RegisterPair.DE, hlData);

            regs.incrementProgramCounter();
            return 1;
        };
    }
}
