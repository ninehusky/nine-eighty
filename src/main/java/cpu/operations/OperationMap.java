package cpu.operations;

import utils.BitUtils;

import java.util.HashMap;
import java.util.Map;

public class OperationMap {
    private final Map<Integer, Operation> operationMap;

    public OperationMap() {
        operationMap = new HashMap<>();

        final Operation noOp = new NoOperation();
        addOperation(operationMap, "NOP", 0x00, noOp);
        addOperation(operationMap, "NOP", 0x08, noOp);
        addOperation(operationMap, "NOP", 0x10, noOp);
        addOperation(operationMap, "NOP", 0x18, noOp);
        addOperation(operationMap, "NOP", 0x20, noOp);
        addOperation(operationMap, "NOP", 0x28, noOp);
        addOperation(operationMap, "NOP", 0x30, noOp);
        addOperation(operationMap, "NOP", 0x38, noOp);
    }

    public Operation fetch(int opcode) {
        if (!operationMap.containsKey(opcode)) {
            throw new IllegalArgumentException("No operation found for opcode " + BitUtils.hexWord(opcode) + "!");
        }
        return operationMap.get(opcode);
    }

    private void addOperation(Map<Integer, Operation> map, String label, int opcode, Operation operation) {
        if (map.containsKey(opcode)) {
            throw new IllegalArgumentException("Operation already assigned to opcode " + opcode);
        }
        map.put(opcode, operation);
    }
}
