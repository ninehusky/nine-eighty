package cpu.instructions.machinecontrol;

import cpu.instructions.Instruction;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

/**
 * POP - The contents of the given register pair are restored from two bytes of memory pointed at by the
 * stack pointer.
 *
 * If the register pair PSW is selected, then the byte of data indicated by the contents
 * of the stack pointer plus one is used to restore the values of the five flags.
 *
 */
public class POP {
    public static Instruction popDataToRegisterPair(RegisterPair pair) {
        return (regs, f, bus) -> {
            int address = regs.getStackPointer().read();
            int lowBits = bus.read(address);
            int highBits = bus.read(address + 1);
            int value = (highBits << 8) | lowBits;

            regs.writePair(pair, value);
            regs.incrementProgramCounter();
            regs.getStackPointer().write(address + 2);
            return 3;
        };
    }

    public static Instruction popDataToFlags() {
        return (regs, f, bus) -> {
            int address = regs.getStackPointer().read();
            int lowBits = bus.read(address + 1);
            int highBits = bus.read(address);

            regs.write(Register.A, lowBits);

            f.setWithMask(highBits);

            regs.incrementProgramCounter();
            regs.getStackPointer().write(address + 2);
            return 3;
        };
    }
}
