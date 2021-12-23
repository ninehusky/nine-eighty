package cpu.instructions.datatransfer;

import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

public class MOV {
    public static Instruction movRegisterToRegister(Register dst, Register src) {
        return (regs, f, bus) -> {
            regs.write(dst, regs.read(src));

            regs.incrementProgramCounter();
            return 1;
        };
    }

    // MOV A, M
    public static Instruction movMemoryToRegister(Register dst) {
        return (regs, f, bus) -> {
            int address = regs.readPair(RegisterPair.HL);
            regs.write(dst, bus.read(address));

            regs.incrementProgramCounter();
            return 1;
        };
    }

    // MOV M, A
    public static Instruction movRegisterToMemory(Register src) {
        return (regs, f, bus) -> {
            int address = regs.readPair(RegisterPair.HL);
            int value = regs.read(src);

            bus.write(address, value);

            regs.incrementProgramCounter();
            return 1;
        };
    }
}
