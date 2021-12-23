package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;

/**
 * LDA - loads the accumulator with the byte located at the address of the immediate
 */
public class LDA {
    public static Instruction loadAccumulatorImmediate() {
        return (regs, f, bus) -> {
            int highBits = bus.read(regs.getProgramCounter().read() + 1);
            int lowBits = bus.read(regs.getProgramCounter().read() + 2);

            int address = (highBits << 8) | lowBits;

            regs.write(Register.A, bus.read(address));

            regs.incrementProgramCounter(3);
            return 4;
        };
    }
}
