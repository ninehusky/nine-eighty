package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class STATest {

    @Test
    @DisplayName("STA stores accumulator value in immediate address")
    void storeAccumulatorImmediate() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        regs.getProgramCounter().write(MemoryBus.RAM_START_ADDRESS);

        // populate immediate
        bus.write(MemoryBus.RAM_START_ADDRESS + 1, 0xAB);
        bus.write(MemoryBus.RAM_START_ADDRESS + 2, 0xD1);

        // populate accumulator
        regs.write(Register.A, 0xDD);

        int before = regs.getProgramCounter().read();
        assertEquals(4, STA.storeAccumulatorImmediate().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();
        assertEquals(3, after - before);

        // verify accumulator stored in immediate address
        assertEquals(0xDD, bus.read(0xABD1));
    }
}