package cpu.instructions;

import cpu.instructions.datatransfer.*;
import cpu.instructions.machinecontrol.NOP;
import cpu.registers.Register;
import cpu.registers.RegisterPair;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the link between the 256 opcodes and their corresponding instructions.
 */
public class OperationMap {
    public static final Map<Integer, Operation> operationMap;

    private static void addCommand(int opcode, String label, Instruction instruction) {
        if (operationMap.containsKey(opcode)) {
            throw new IllegalArgumentException("Opcode " + Integer.toHexString(opcode) + " already assigned!");
        }
        operationMap.put(opcode, new Operation(label, instruction));
    }

    static {
        operationMap = new HashMap<>();

        // let's test some no ops
        addCommand(0x00, "NOP", NOP.NoOperation());
        addCommand(0x01, "LXI B, d16", LXI.loadWordToPair(RegisterPair.BC));
        addCommand(0x02, "STAX B", STAX.StoreAccumulatorInMemory(RegisterPair.BC));

        addCommand(0x06, "MVI B, d8", MVI.loadRegisterWithImmediate(Register.B));

        addCommand(0x08, "NOP", NOP.NoOperation());

        addCommand(0x0A, "LDAX B", LDAX.loadAccumulator(RegisterPair.BC));

        addCommand(0x0E, "MVI C, d8", MVI.loadRegisterWithImmediate(Register.C));

        addCommand(0x10, "NOP", NOP.NoOperation());
        addCommand(0x11, "LXI D, d16", LXI.loadWordToPair(RegisterPair.DE));
        addCommand(0x12, "STAX D", STAX.StoreAccumulatorInMemory(RegisterPair.DE));

        addCommand(0x16, "MVI D, d8", MVI.loadRegisterWithImmediate(Register.D));

        addCommand(0x18, "NOP", NOP.NoOperation());

        addCommand(0x1A, "LDAX D", LDAX.loadAccumulator(RegisterPair.DE));

        addCommand(0x1E, "MVI E, d8", MVI.loadRegisterWithImmediate(Register.E));

        addCommand(0x20, "NOP", NOP.NoOperation());
        addCommand(0x21, "LXI H, d16", LXI.loadWordToPair(RegisterPair.HL));
        addCommand(0x22, "SHLD a16", SHLD.storeHL());

        addCommand(0x26, "MVI H, d8", MVI.loadRegisterWithImmediate(Register.H));

        addCommand(0x28, "NOP", NOP.NoOperation());

        addCommand(0x30, "NOP", NOP.NoOperation());
        addCommand(0x31, "LXI SP, d16", LXI.loadWordToStackPointer());

        addCommand(0x36, "MVI M, d8", MVI.loadMemoryWithImmediate());

        addCommand(0x38, "NOP", NOP.NoOperation());

        addCommand(0x3E, "MVI A, d8", MVI.loadRegisterWithImmediate(Register.A));
    }
}
