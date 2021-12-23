package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SHLDTest {
    @Test
    @DisplayName("SHLD correctly populates address with L and H register values")
    void storeHL() {
        Registers regs = new Registers();

        Flags f = Mockito.mock(Flags.class);

        MemoryBus bus = new MemoryBus(new byte[1]);

        // set program counter to writeable memory
        regs.getProgramCounter().write(0xABD1);

        // populate immediate as 0xABCD (the address that SHLD writes to)
        bus.write(0xABD2, 0xAB);
        bus.write(0xABD3, 0xCD);

        // populate value to be written
        regs.write(Register.L, 0xB0);
        regs.write(Register.H, 0xBA);

        int before = regs.getProgramCounter().read();
        assertEquals(5, SHLD.storeHL().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        // verify that 0xB0BA is the word at the address given by the immediate
        assertEquals(0xB0, bus.read(0xABCD));
        assertEquals(0xBA, bus.read(0xABCD + 1));

        assertEquals(3, after - before);
    }
}