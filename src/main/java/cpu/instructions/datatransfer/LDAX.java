package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

/**
 * Loads the accumulator with the address located at the given register pair.
 */
public class LDAX {
    public static Instruction loadAccumulator(RegisterPair pair) {
        return (r, f, bus) -> {
            int address = r.readPair(pair);
            r.write(Register.A, bus.read(address));
            r.incrementProgramCounter();
            return 2;
        };
    }
}
