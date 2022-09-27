package cpu.instructions;

import cpu.instructions.datatransfer.*;
import cpu.instructions.machinecontrol.HLT;
import cpu.instructions.machinecontrol.NOP;
import cpu.instructions.machinecontrol.POP;
import cpu.instructions.machinecontrol.PUSH;
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

        addCommand(0x00, "NOP", NOP.noOperation());
        addCommand(0x01, "LXI B, d16", LXI.loadWordToPair(RegisterPair.BC));
        addCommand(0x02, "STAX B", STAX.StoreAccumulatorInMemory(RegisterPair.BC));

        addCommand(0x06, "MVI B, d8", MVI.loadRegisterWithImmediate(Register.B));

        addCommand(0x08, "NOP", NOP.noOperation());

        addCommand(0x0A, "LDAX B", LDAX.loadAccumulator(RegisterPair.BC));

        addCommand(0x0E, "MVI C, d8", MVI.loadRegisterWithImmediate(Register.C));

        addCommand(0x10, "NOP", NOP.noOperation());
        addCommand(0x11, "LXI D, d16", LXI.loadWordToPair(RegisterPair.DE));
        addCommand(0x12, "STAX D", STAX.StoreAccumulatorInMemory(RegisterPair.DE));

        addCommand(0x16, "MVI D, d8", MVI.loadRegisterWithImmediate(Register.D));

        addCommand(0x18, "NOP", NOP.noOperation());

        addCommand(0x1A, "LDAX D", LDAX.loadAccumulator(RegisterPair.DE));

        addCommand(0x1E, "MVI E, d8", MVI.loadRegisterWithImmediate(Register.E));

        addCommand(0x20, "NOP", NOP.noOperation());
        addCommand(0x21, "LXI H, d16", LXI.loadWordToPair(RegisterPair.HL));
        addCommand(0x22, "SHLD a16", SHLD.storeHL());

        addCommand(0x26, "MVI H, d8", MVI.loadRegisterWithImmediate(Register.H));

        addCommand(0x28, "NOP", NOP.noOperation());

        addCommand(0x2A, "LHLD a16", LHLD.loadHLImmediate());

        addCommand(0x30, "NOP", NOP.noOperation());
        addCommand(0x31, "LXI SP, d16", LXI.loadWordToStackPointer());
        addCommand(0x32, "STA, a16", STA.storeAccumulatorImmediate());

        addCommand(0x36, "MVI M, d8", MVI.loadMemoryWithImmediate());

        addCommand(0x38, "NOP", NOP.noOperation());

        addCommand(0x3A, "LDA a16", LDA.loadAccumulatorImmediate());

        addCommand(0x3E, "MVI A, d8", MVI.loadRegisterWithImmediate(Register.A));

        addCommand(0x40, "MOV B, B", MOV.movRegisterToRegister(Register.B, Register.B));
        addCommand(0x41, "MOV B, C", MOV.movRegisterToRegister(Register.B, Register.C));
        addCommand(0x42, "MOV B, D", MOV.movRegisterToRegister(Register.B, Register.D));
        addCommand(0x43, "MOV B, E", MOV.movRegisterToRegister(Register.B, Register.E));
        addCommand(0x44, "MOV B, H", MOV.movRegisterToRegister(Register.B, Register.H));
        addCommand(0x45, "MOV B, L", MOV.movRegisterToRegister(Register.B, Register.L));
        addCommand(0x46, "MOV B, M", MOV.movMemoryToRegister(Register.B));
        addCommand(0x47, "MOV B, A", MOV.movRegisterToRegister(Register.B, Register.A));

        addCommand(0x48, "MOV C, B", MOV.movRegisterToRegister(Register.C, Register.B));
        addCommand(0x49, "MOV C, C", MOV.movRegisterToRegister(Register.C, Register.C));
        addCommand(0x4A, "MOV C, D", MOV.movRegisterToRegister(Register.C, Register.D));
        addCommand(0x4B, "MOV C, E", MOV.movRegisterToRegister(Register.C, Register.E));
        addCommand(0x4C, "MOV C, H", MOV.movRegisterToRegister(Register.C, Register.H));
        addCommand(0x4D, "MOV C, L", MOV.movRegisterToRegister(Register.C, Register.L));
        addCommand(0x4E, "MOV C, M", MOV.movMemoryToRegister(Register.C));
        addCommand(0x4F, "MOV C, A", MOV.movRegisterToRegister(Register.C, Register.A));

        addCommand(0x50, "MOV D, B", MOV.movRegisterToRegister(Register.D, Register.B));
        addCommand(0x51, "MOV D, C", MOV.movRegisterToRegister(Register.D, Register.C));
        addCommand(0x52, "MOV D, D", MOV.movRegisterToRegister(Register.D, Register.D));
        addCommand(0x53, "MOV D, E", MOV.movRegisterToRegister(Register.D, Register.E));
        addCommand(0x54, "MOV D, H", MOV.movRegisterToRegister(Register.D, Register.H));
        addCommand(0x55, "MOV D, L", MOV.movRegisterToRegister(Register.D, Register.L));
        addCommand(0x56, "MOV D, M", MOV.movMemoryToRegister(Register.D));
        addCommand(0x57, "MOV D, A", MOV.movRegisterToRegister(Register.D, Register.A));

        addCommand(0x58, "MOV E, B", MOV.movRegisterToRegister(Register.E, Register.B));
        addCommand(0x59, "MOV E, C", MOV.movRegisterToRegister(Register.E, Register.C));
        addCommand(0x5A, "MOV E, D", MOV.movRegisterToRegister(Register.E, Register.D));
        addCommand(0x5B, "MOV E, E", MOV.movRegisterToRegister(Register.E, Register.E));
        addCommand(0x5C, "MOV E, H", MOV.movRegisterToRegister(Register.E, Register.H));
        addCommand(0x5D, "MOV E, L", MOV.movRegisterToRegister(Register.E, Register.L));
        addCommand(0x5E, "MOV E, M", MOV.movMemoryToRegister(Register.E));
        addCommand(0x5F, "MOV E, A", MOV.movRegisterToRegister(Register.E, Register.A));

        addCommand(0x60, "MOV H, B", MOV.movRegisterToRegister(Register.H, Register.B));
        addCommand(0x61, "MOV H, C", MOV.movRegisterToRegister(Register.H, Register.C));
        addCommand(0x62, "MOV H, D", MOV.movRegisterToRegister(Register.H, Register.D));
        addCommand(0x63, "MOV H, E", MOV.movRegisterToRegister(Register.H, Register.E));
        addCommand(0x64, "MOV H, H", MOV.movRegisterToRegister(Register.H, Register.H));
        addCommand(0x65, "MOV H, L", MOV.movRegisterToRegister(Register.H, Register.L));
        addCommand(0x66, "MOV H, M", MOV.movMemoryToRegister(Register.H));
        addCommand(0x67, "MOV H, A", MOV.movRegisterToRegister(Register.H, Register.A));

        addCommand(0x68, "MOV L, B", MOV.movRegisterToRegister(Register.L, Register.B));
        addCommand(0x69, "MOV L, C", MOV.movRegisterToRegister(Register.L, Register.C));
        addCommand(0x6A, "MOV L, D", MOV.movRegisterToRegister(Register.L, Register.D));
        addCommand(0x6B, "MOV L, E", MOV.movRegisterToRegister(Register.L, Register.E));
        addCommand(0x6C, "MOV L, H", MOV.movRegisterToRegister(Register.L, Register.H));
        addCommand(0x6D, "MOV L, L", MOV.movRegisterToRegister(Register.L, Register.L));
        addCommand(0x6E, "MOV L, M", MOV.movMemoryToRegister(Register.L));
        addCommand(0x6F, "MOV L, A", MOV.movRegisterToRegister(Register.L, Register.A));

        addCommand(0x70, "MOV M, B", MOV.movRegisterToMemory(Register.B));
        addCommand(0x71, "MOV M, C", MOV.movRegisterToMemory(Register.C));
        addCommand(0x72, "MOV M, D", MOV.movRegisterToMemory(Register.D));
        addCommand(0x73, "MOV M, E", MOV.movRegisterToMemory(Register.E));
        addCommand(0x74, "MOV M, H", MOV.movRegisterToMemory(Register.H));
        addCommand(0x75, "MOV M, L", MOV.movRegisterToMemory(Register.L));

        addCommand(0x76, "HLT", HLT.haltInstruction());

        addCommand(0x77, "MOV M, A", MOV.movRegisterToMemory(Register.A));

        addCommand(0x88, "MOV A, B", MOV.movRegisterToRegister(Register.A, Register.B));
        addCommand(0x89, "MOV A, C", MOV.movRegisterToRegister(Register.A, Register.C));
        addCommand(0x8A, "MOV A, D", MOV.movRegisterToRegister(Register.A, Register.D));
        addCommand(0x8B, "MOV A, E", MOV.movRegisterToRegister(Register.A, Register.E));
        addCommand(0x8C, "MOV A, H", MOV.movRegisterToRegister(Register.A, Register.H));
        addCommand(0x8D, "MOV A, L", MOV.movRegisterToRegister(Register.A, Register.L));
        addCommand(0x8E, "MOV A, M", MOV.movMemoryToRegister(Register.A));
        addCommand(0x8F, "MOV A, A", MOV.movRegisterToRegister(Register.A, Register.A));

        addCommand(0xC1, "POP B", POP.popDataToRegisterPair(RegisterPair.BC));

        addCommand(0xC5, "PUSH B", PUSH.pushDataToStack(RegisterPair.BC));

        addCommand(0xD1, "POP D", POP.popDataToRegisterPair(RegisterPair.DE));

        addCommand(0xD5, "PUSH D", PUSH.pushDataToStack(RegisterPair.DE));

        addCommand(0xE1, "POP H", POP.popDataToRegisterPair(RegisterPair.HL));

        addCommand(0xE5, "PUSH H", PUSH.pushDataToStack(RegisterPair.HL));

        addCommand(0xEB, "XCHG", XCHG.exchangeData());

        addCommand(0xF1, "POP PSW", POP.popDataToFlags());

        addCommand(0xF5, "PUSH PSW", PUSH.pushDataAndFlagsToStack());
    }
}
