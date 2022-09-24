package cpu.instructions.machinecontrol;

import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NOPTest {
    @Test
    @DisplayName("NOP runs in correct number of cycles")
    void testNopCycles() {
        Registers regs = Mockito.mock(Registers.class);
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        assertEquals(1, NOP.noOperation().execute(regs, flags, bus));
    }

    @Test
    @DisplayName("NOP correctly increments pc")
    void testNopLength() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        int before = regs.getProgramCounter().read();
        NOP.noOperation().execute(regs, flags, bus);
        int after = regs.getProgramCounter().read();

        assertEquals(1, after - before);
    }
}