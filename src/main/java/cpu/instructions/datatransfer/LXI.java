package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.instructions.Instruction;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;

/**
 * Loads register pair/stack pointer with a 16-bit immediate.
 * Flags set: none
 */
public class LXI {
    public static Instruction loadWordToPair(RegisterPair pair) {
        return (r, f, bus) -> {
            int highBits = bus.read(r.getProgramCounter().read());
            int lowBits = bus.read(r.getProgramCounter().read() + 1);
            r.writePair(pair, (highBits << 8) | lowBits);

            r.incrementProgramCounter(3);
            return 3;
        };
    }

    public static Instruction loadWordToStackPointer() {
        return (r, f, bus) -> {
            int highBits = bus.read(r.getProgramCounter().read());
            int lowBits = bus.read(r.getProgramCounter().read() + 1);
            r.getStackPointer().write((highBits << 8) | lowBits);

            r.incrementProgramCounter(3);
            return 3;
        };
    }
}
