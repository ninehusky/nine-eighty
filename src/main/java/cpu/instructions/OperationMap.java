package cpu.instructions;

import cpu.instructions.machinecontrol.NOP;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the link between the 256 opcodes and their corresponding instructions.
 */
public class OperationMap {
    public static final Map<Integer, Operation> operationMap;

    private static void addCommand(int opcode, String label, Instruction instruction) {
        operationMap.put(opcode, new Operation(label, instruction));
    }

    static {
        operationMap = new HashMap<>();

        // let's test some no ops
        addCommand(0x00, "NOP", NOP.NoOperation());

        addCommand(0x08, "NOP", NOP.NoOperation());

        addCommand(0x10, "NOP", NOP.NoOperation());

        addCommand(0x18, "NOP", NOP.NoOperation());

        addCommand(0x20, "NOP", NOP.NoOperation());

        addCommand(0x28, "NOP", NOP.NoOperation());

        addCommand(0x30, "NOP", NOP.NoOperation());

        addCommand(0x38, "NOP", NOP.NoOperation());

    }
}
