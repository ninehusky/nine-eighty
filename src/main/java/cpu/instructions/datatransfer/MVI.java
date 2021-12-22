package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

public class MVI {
    public static Instruction loadRegisterWithImmediate(Register reg) {
        return (r, f, bus) -> {
            int immediate = bus.read(r.getProgramCounter().read() + 1);
            r.write(reg, immediate);
            r.incrementProgramCounter(2);
            return 2;
        };
    }

    public static Instruction loadMemoryWithImmediate() {
        // Mem[HL] = immediate
        return (r, f, bus) -> {
            int immediate = bus.read(r.getProgramCounter().read() + 1);
            int address = r.readPair(RegisterPair.HL);
            bus.write(address, immediate);
            r.incrementProgramCounter(2);
            return 2;
        };
    }
}
