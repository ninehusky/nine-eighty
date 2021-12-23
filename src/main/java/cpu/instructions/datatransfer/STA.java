package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;

/**
 * Stores the value of the accumulator in an immediate address.
 */
public class STA {
    public static Instruction storeAccumulatorImmediate() {
        return (regs, f, bus) -> {
            int highBits = bus.read(regs.getProgramCounter().read() + 1);
            int lowBits = bus.read(regs.getProgramCounter().read() + 2);

            int address = (highBits << 8) | lowBits;

            bus.write(address, regs.read(Register.A));

            regs.incrementProgramCounter(3);
            return 4;
        };
    }
}
