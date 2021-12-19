package cpu.instructions;

import cpu.registers.Registers;

@FunctionalInterface
public interface Instruction {
    public int execute(Registers r);
}