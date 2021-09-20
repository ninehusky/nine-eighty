package cpu;

import cpu.flags.Flags;
import cpu.operations.datatransfer.*;
import cpu.operations.machinecontrol.NoOperation;
import cpu.operations.Operation;
import cpu.operations.OperationMap;
import cpu.registers.ProgramCounter;
import cpu.registers.Registers;
import cpu.registers.StackPointer;
import memory.MemoryBus;
import utils.BitUtils;

import java.util.HashMap;
import java.util.Map;

public class CPU {
    private final Registers regs;
    private final Flags flags;
    private final ProgramCounter pc;
    private final StackPointer sp;
    private final MemoryBus memoryBus;

    public final OperationMap operationMap; // TODO: private

    public CPU(Registers regs, Flags flags, MemoryBus memoryBus, ProgramCounter pc, StackPointer sp) {
        this.regs = regs;
        this.flags = flags;
        this.pc = pc;
        this.sp = sp;
        this.memoryBus = memoryBus;

        this.operationMap = new IntelOperationMap();
    }

    public void execute() {
        int opcode = memoryBus.getByte(pc.getValue());
        Operation currentOperation = operationMap.fetch(opcode);

        int cycles = currentOperation.execute(regs, flags, memoryBus, pc, sp);
    }

    public static void main(String[] args) {
        CPU cpu;
        long totalTime = 0;
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            cpu = new CPU(new Registers(), new Flags(), new MemoryBus(), new ProgramCounter(), new StackPointer());
            long endTime = System.currentTimeMillis();
            if (i > 2) {
                System.out.println(endTime - startTime);
                totalTime += endTime - startTime;
            }
        }

        System.out.println("Average time for CPU construction: " + totalTime / 7);
    }


    private class IntelOperationMap implements OperationMap {
        private final Map<Integer, Operation> operationMap;

        public IntelOperationMap() {
            operationMap = new HashMap<>();

            final Operation noOp = new NoOperation();
            addOperation(operationMap, "NOP", 0x00, noOp);
            addOperation(operationMap, "LXI B, d16", 0x01, new LoadRegisterPairImmediate(regs.getB(), regs.getC()));
            addOperation(operationMap, "STAX B", 0x02, new StoreAInRegisterPair(regs.getB(), regs.getC()));

            addOperation(operationMap, "NOP", 0x08, noOp);
            addOperation(operationMap, "NOP", 0x10, noOp);
            addOperation(operationMap, "LXI D, d16", 0x11, new LoadRegisterPairImmediate(regs.getD(), regs.getE()));
            addOperation(operationMap, "STAX D", 0x12, new StoreAInRegisterPair(regs.getD(), regs.getE()));

            addOperation(operationMap, "NOP", 0x18, noOp);

            addOperation(operationMap, "NOP", 0x20, noOp);
            addOperation(operationMap, "LXI H, d16", 0x21, new LoadRegisterPairImmediate(regs.getH(), regs.getL()));

            addOperation(operationMap, "NOP", 0x28, noOp);

            addOperation(operationMap, "NOP", 0x30, noOp);
            addOperation(operationMap, "LXI SP, d16", 0x31, new LoadStackPointerImmediate());

            addOperation(operationMap, "NOP", 0x38, noOp);

            addOperation(operationMap, "MOV B, B", 0x40, new MovRegisterToRegister(regs.getB(), regs.getB()));
            addOperation(operationMap, "MOV B, C", 0x41, new MovRegisterToRegister(regs.getB(), regs.getC()));
            addOperation(operationMap, "MOV B, D", 0x42, new MovRegisterToRegister(regs.getB(), regs.getD()));
            addOperation(operationMap, "MOV B, E", 0x43, new MovRegisterToRegister(regs.getB(), regs.getE()));
            addOperation(operationMap, "MOV B, H", 0x44, new MovRegisterToRegister(regs.getB(), regs.getH()));
            addOperation(operationMap, "MOV B, L", 0x45, new MovRegisterToRegister(regs.getB(), regs.getL()));
            addOperation(operationMap, "MOV B, M", 0x46, new MovRegisterToMemory(regs.getB()));
            addOperation(operationMap, "MOV B, A", 0x47, new MovRegisterToRegister(regs.getB(), regs.getA()));

            addOperation(operationMap, "MOV C, B", 0x48, new MovRegisterToRegister(regs.getC(), regs.getB()));
            addOperation(operationMap, "MOV C, C", 0x49, new MovRegisterToRegister(regs.getC(), regs.getC()));
            addOperation(operationMap, "MOV C, D", 0x4A, new MovRegisterToRegister(regs.getC(), regs.getD()));
            addOperation(operationMap, "MOV C, E", 0x4B, new MovRegisterToRegister(regs.getC(), regs.getE()));
            addOperation(operationMap, "MOV C, H", 0x4C, new MovRegisterToRegister(regs.getC(), regs.getH()));
            addOperation(operationMap, "MOV C, L", 0x4D, new MovRegisterToRegister(regs.getC(), regs.getL()));
            addOperation(operationMap, "MOV C, M", 0x4E, new MovRegisterToMemory(regs.getC()));
            addOperation(operationMap, "MOV C, A", 0x4F, new MovRegisterToRegister(regs.getC(), regs.getA()));

            addOperation(operationMap, "MOV D, B", 0x50, new MovRegisterToRegister(regs.getD(), regs.getB()));
            addOperation(operationMap, "MOV D, C", 0x51, new MovRegisterToRegister(regs.getD(), regs.getC()));
            addOperation(operationMap, "MOV D, D", 0x52, new MovRegisterToRegister(regs.getD(), regs.getD()));
            addOperation(operationMap, "MOV D, E", 0x53, new MovRegisterToRegister(regs.getD(), regs.getE()));
            addOperation(operationMap, "MOV D, H", 0x54, new MovRegisterToRegister(regs.getD(), regs.getH()));
            addOperation(operationMap, "MOV D, L", 0x55, new MovRegisterToRegister(regs.getD(), regs.getL()));
            addOperation(operationMap, "MOV D, M", 0x56, new MovRegisterToMemory(regs.getD()));
            addOperation(operationMap, "MOV D, A", 0x57, new MovRegisterToRegister(regs.getD(), regs.getA()));

            addOperation(operationMap, "MOV E, B", 0x58, new MovRegisterToRegister(regs.getE(), regs.getB()));
            addOperation(operationMap, "MOV E, C", 0x59, new MovRegisterToRegister(regs.getE(), regs.getC()));
            addOperation(operationMap, "MOV E, D", 0x5A, new MovRegisterToRegister(regs.getE(), regs.getD()));
            addOperation(operationMap, "MOV E, E", 0x5B, new MovRegisterToRegister(regs.getE(), regs.getE()));
            addOperation(operationMap, "MOV E, H", 0x5C, new MovRegisterToRegister(regs.getE(), regs.getH()));
            addOperation(operationMap, "MOV E, L", 0x5D, new MovRegisterToRegister(regs.getE(), regs.getL()));
            addOperation(operationMap, "MOV E, M", 0x5E, new MovRegisterToMemory(regs.getE()));
            addOperation(operationMap, "MOV E, A", 0x5F, new MovRegisterToRegister(regs.getE(), regs.getA()));

            addOperation(operationMap, "MOV H, B", 0x60, new MovRegisterToRegister(regs.getH(), regs.getB()));
            addOperation(operationMap, "MOV H, C", 0x61, new MovRegisterToRegister(regs.getH(), regs.getC()));
            addOperation(operationMap, "MOV H, D", 0x62, new MovRegisterToRegister(regs.getH(), regs.getD()));
            addOperation(operationMap, "MOV H, E", 0x63, new MovRegisterToRegister(regs.getH(), regs.getE()));
            addOperation(operationMap, "MOV H, H", 0x64, new MovRegisterToRegister(regs.getH(), regs.getH()));
            addOperation(operationMap, "MOV H, L", 0x65, new MovRegisterToRegister(regs.getH(), regs.getL()));
            addOperation(operationMap, "MOV H, M", 0x66, new MovRegisterToMemory(regs.getH()));
            addOperation(operationMap, "MOV H, A", 0x67, new MovRegisterToRegister(regs.getH(), regs.getA()));

            addOperation(operationMap, "MOV L, B", 0x68, new MovRegisterToRegister(regs.getL(), regs.getB()));
            addOperation(operationMap, "MOV L, C", 0x69, new MovRegisterToRegister(regs.getL(), regs.getC()));
            addOperation(operationMap, "MOV L, D", 0x6A, new MovRegisterToRegister(regs.getL(), regs.getD()));
            addOperation(operationMap, "MOV L, E", 0x6B, new MovRegisterToRegister(regs.getL(), regs.getE()));
            addOperation(operationMap, "MOV L, H", 0x6C, new MovRegisterToRegister(regs.getL(), regs.getH()));
            addOperation(operationMap, "MOV L, L", 0x6D, new MovRegisterToRegister(regs.getL(), regs.getL()));
            addOperation(operationMap, "MOV L, M", 0x6E, new MovRegisterToMemory(regs.getL()));
            addOperation(operationMap, "MOV L, A", 0x6F, new MovRegisterToRegister(regs.getL(), regs.getA()));

            addOperation(operationMap, "MOV M, B", 0x70, new MovMemoryToRegister(regs.getB()));
            addOperation(operationMap, "MOV M, C", 0x71, new MovMemoryToRegister(regs.getC()));
            addOperation(operationMap, "MOV M, D", 0x72, new MovMemoryToRegister(regs.getD()));
            addOperation(operationMap, "MOV M, E", 0x73, new MovMemoryToRegister(regs.getE()));
            addOperation(operationMap, "MOV M, H", 0x74, new MovMemoryToRegister(regs.getH()));
            addOperation(operationMap, "MOV M, L", 0x75, new MovMemoryToRegister(regs.getL()));

            // TODO: implement the HLT operation
            addOperation(operationMap, "HLT", 0x76, new NoOperation());

            addOperation(operationMap, "MOV M, A", 0x77, new MovMemoryToRegister(regs.getA()));

            addOperation(operationMap, "MOV A, B", 0x78, new MovRegisterToRegister(regs.getA(), regs.getB()));
            addOperation(operationMap, "MOV A, C", 0x79, new MovRegisterToRegister(regs.getA(), regs.getC()));
            addOperation(operationMap, "MOV A, D", 0x7A, new MovRegisterToRegister(regs.getA(), regs.getD()));
            addOperation(operationMap, "MOV A, E", 0x7B, new MovRegisterToRegister(regs.getA(), regs.getE()));
            addOperation(operationMap, "MOV A, H", 0x7C, new MovRegisterToRegister(regs.getA(), regs.getH()));
            addOperation(operationMap, "MOV A, L", 0x7D, new MovRegisterToRegister(regs.getA(), regs.getL()));
            addOperation(operationMap, "MOV A, M", 0x7E, new MovRegisterToMemory(regs.getA()));
            addOperation(operationMap, "MOV A, A", 0x7F, new MovRegisterToRegister(regs.getA(), regs.getA()));
        }

        @Override
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
}
