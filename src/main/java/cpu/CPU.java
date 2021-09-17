package cpu;

import cpu.flags.Flags;
import cpu.operations.Operation;
import cpu.operations.NoOperation;
import cpu.operations.OperationMap;
import cpu.registers.ProgramCounter;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;

import java.util.ArrayList;
import java.util.Arrays;

public class CPU {
    private final Registers regs;
    private final Flags flags;
    private final ProgramCounter pc;
    private final StackPointer sp;
    private final MemoryBus memoryBus;

    public final OperationMap operationMap; // TODO: private

    public CPU(Registers regs, Flags flags, ProgramCounter pc, StackPointer sp, MemoryBus memoryBus) {
        this.regs = regs;
        this.flags = flags;
        this.pc = pc;
        this.sp = sp;
        this.memoryBus = memoryBus;

        this.operationMap = new OperationMap();
    }

    public static void main(String[] args) {
        CPU cpu = new CPU(new Registers(), new Flags(), new ProgramCounter(), new StackPointer(), new MemoryBus());
    }


}
