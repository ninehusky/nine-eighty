package cpu.instructions;

import cpu.instructions.datatransfer.LXI;
import cpu.instructions.datatransfer.STAX;
import cpu.instructions.machinecontrol.NOP;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;

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

        addCommand(0x08, "NOP", NOP.NoOperation());

        addCommand(0x10, "NOP", NOP.NoOperation());
        addCommand(0x11, "LXI D, d16", LXI.loadWordToPair(RegisterPair.DE));
        addCommand(0x12, "STAX D", STAX.StoreAccumulatorInMemory(RegisterPair.DE));

        addCommand(0x18, "NOP", NOP.NoOperation());

        addCommand(0x20, "NOP", NOP.NoOperation());
        addCommand(0x21, "LXI H, d16", LXI.loadWordToPair(RegisterPair.HL));

        addCommand(0x28, "NOP", NOP.NoOperation());

        addCommand(0x30, "NOP", NOP.NoOperation());
        addCommand(0x31, "LXI SP, d16", LXI.loadWordToStackPointer());

        addCommand(0x38, "NOP", NOP.NoOperation());
    }
}
