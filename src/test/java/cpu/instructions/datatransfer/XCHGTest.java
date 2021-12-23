package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class XCHGTest {
    @Test
    @DisplayName("Verify register data exchanged")
    void exchangeData() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        regs.writePair(RegisterPair.DE, 0xB0BA);
        regs.writePair(RegisterPair.HL, 0xABD1);

        int before = regs.getProgramCounter().read();
        assertEquals(1, XCHG.exchangeData().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        // verify data properly exchanged
        assertEquals(0xB0BA, regs.readPair(RegisterPair.HL));
        assertEquals(0xABD1, regs.readPair(RegisterPair.DE));

        assertEquals(1, after - before);
    }
}