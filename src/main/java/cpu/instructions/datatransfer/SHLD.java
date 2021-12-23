package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;

/**
 * The contents of the L register are stored at the memory address formed by the accompanied word.
 * The contents of the H register are stored one address higher.
 */
public class SHLD {
    public static Instruction storeHL() {
        return (regs, f, bus) -> {
            int highBits = bus.read(regs.getProgramCounter().read() + 1);
            int lowBits = bus.read(regs.getProgramCounter().read() + 2);
            int address = (highBits << 8) | lowBits;

            bus.write(address, regs.read(Register.L));
            bus.write(address + 1, regs.read(Register.H));

            regs.incrementProgramCounter(3);
            return 5;
        };
    }
}
