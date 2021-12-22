package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;

/**
 * STAX - store accumulator
 * Stores the value of the accumulator (<code>Register.A</code>) in the memory address
 * specified in a register pair.
 */
public class STAX {
    public static Instruction StoreAccumulatorInMemory(RegisterPair pair) {
        return (r, f, bus) -> {
            // assemble address
            int address = r.readPair(pair);

            bus.write(address, r.read(Register.A));
            r.incrementProgramCounter();
            return 2;
        };
    }
}
