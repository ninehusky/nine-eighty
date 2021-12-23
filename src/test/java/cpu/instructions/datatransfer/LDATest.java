package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LDATest {

    @Test
    @DisplayName("LDA populates accumulator with byte present at immediate address")
    void loadAccumulatorImmediate() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        regs.getProgramCounter().write(MemoryBus.RAM_START_ADDRESS);

        bus.write(MemoryBus.RAM_START_ADDRESS + 1, 0xAB);
        bus.write(MemoryBus.RAM_START_ADDRESS + 2, 0xD1);

        bus.write(0xABD1, 0x69);

        int before = regs.getProgramCounter().read();
        assertEquals(4, LDA.loadAccumulatorImmediate().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();
        assertEquals(3, after - before);
        assertEquals(0x69, regs.read(Register.A));
    }
}