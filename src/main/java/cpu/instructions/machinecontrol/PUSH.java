package cpu.instructions.machinecontrol;

import cpu.flags.Flags;
import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

/**
 * PUSH - The contents of the specified register pair are saved in two bytes of memory indicated by the stack
 * pointer SP.
 */
public class PUSH {
    public static Instruction pushDataToStack(RegisterPair pair) {
        return (regs, f, bus) -> {
            int address = regs.getStackPointer().read();
            int value = regs.readPair(pair);
            int highBits = (value >>> 8);
            int lowBits = value & 0xFF;

            bus.write(address - 1, highBits);
            bus.write(address - 2, lowBits);

            regs.getStackPointer().write(regs.getStackPointer().read() - 2);
            regs.incrementProgramCounter();
            return 3;
        };
    }

    public static Instruction pushDataAndFlagsToStack() {
        return (regs, f, bus) -> {
            int address = regs.getStackPointer().read();
            int firstByte = regs.read(Register.A);
            int secondByte = Flags.asInt(f);

            bus.write(address - 1, firstByte);
            bus.write(address - 2, secondByte);

            regs.getStackPointer().write(regs.getStackPointer().read() - 2);
            regs.incrementProgramCounter();
            return 3;
        };
    }
}
