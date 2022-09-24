package cpu;

import cpu.flags.Flags;
import cpu.instructions.Operation;
import cpu.instructions.OperationMap;
import cpu.registers.Registers;
import mmu.MemoryBus;

import java.util.Map;

/**
 * Represents an Intel 8080.
 */
public class CPU {
    private Registers regs;       // seven 8-bit registers
    private Flags flags;          // condition bits
    private MemoryBus bus;

    private final Map<Integer, Operation> operationMap;

    // TODO: switch this bad boy off
    private static final boolean DEBUG_MODE = true;

    public CPU(Registers regs, Flags flags, MemoryBus bus) {
        this.regs = regs;
        this.flags = flags;
        this.bus = bus;
        operationMap = OperationMap.operationMap;
    }

    /**
     * Returns the current operation.
     */
    public Operation getCurrentOperation() {
        int opcode = bus.read(regs.getProgramCounter().read());
        return operationMap.get(opcode);
    }

    /**
     * Fetches and executes exactly one operation.
     */
    public void execute() {
        // fetch instruction
        // TODO!: factor into account the STOPPED CPU state
        Operation current = getCurrentOperation();
        if (DEBUG_MODE) {
            System.out.println("PC: " + Integer.toHexString(regs.getProgramCounter().read()));
            System.out.println("Opcode: " + Integer.toHexString(bus.read(regs.getProgramCounter().read())));
            System.out.println("Operation: " + current);
        }
        current.execute(regs, flags, bus);
    }

    /**
     * Resets the CPU to the Space Invaders' original settings.
     */
    public void reset() {
        // TODO: i should do this.
        throw new UnsupportedOperationException();
    }
}
