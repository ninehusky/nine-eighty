package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LHLDTest {

    @Test
    @DisplayName("LHLD correctly loads H and L")
    void loadHLImmediate() {
        Registers regs = new Registers();
        regs.getProgramCounter().write(MemoryBus.RAM_START_ADDRESS);

        Flags f = Mockito.mock(Flags.class);

        MemoryBus bus = new MemoryBus(new byte[1]);

        bus.write(MemoryBus.RAM_START_ADDRESS + 1, 0xAB);
        bus.write(MemoryBus.RAM_START_ADDRESS + 2, 0xD1);

        int before = regs.getProgramCounter().read();
        assertEquals(5, LHLD.loadHLImmediate().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(0xAB, regs.read(Register.L));
        assertEquals(0xD1, regs.read(Register.H));

        assertEquals(3, after - before);
    }
}