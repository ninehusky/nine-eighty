package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class STAXTest {
    @Test
    @DisplayName("STAX stores accumulator in memory addressed by pair")
    void testStaxBehavior() {
        Registers regs = new Registers();
        regs.write(Register.A, 0xDE);
        regs.write(Register.B, 0xAB);
        regs.write(Register.C, 0xCD);

        Flags f = Mockito.mock(Flags.class);

        // feed this bad boy a dummy ROM
        MemoryBus bus = new MemoryBus(new byte[MemoryBus.ROM_SIZE_IN_BYTES]);

        STAX.StoreAccumulatorInMemory(RegisterPair.BC).execute(regs, f, bus);
        assertEquals(bus.read(0xABCD), 0xDE);

        assertEquals(0xAB, regs.read(Register.B));
        assertEquals(0xCD, regs.read(Register.C));
    }

    @Test
    @DisplayName("STAX has correct opcode length")
    void testStaxLength() {
        Registers regs = new Registers();
        regs.write(Register.A, 0xDE);
        regs.write(Register.B, 0xAB);
        Flags f = Mockito.mock(Flags.class);
        // feed this bad boy a dummy ROM
        MemoryBus bus = new MemoryBus(new byte[MemoryBus.ROM_SIZE_IN_BYTES]);

        int before = regs.getProgramCounter().read();
        assertEquals(2, STAX.StoreAccumulatorInMemory(RegisterPair.BC).execute(regs, f, bus));
        int after = regs.getProgramCounter().read();
        assertEquals(1, after - before);
    }
}