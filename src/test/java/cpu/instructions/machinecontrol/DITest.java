package cpu.instructions.machinecontrol;

import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DITest {
    @Test
    @DisplayName("DI disables interrupts")
    public void enablesInterrupts() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        regs.setInterruptsEnabled(true);

        assertEquals(true, regs.isInterruptsEnabled());

        DI.disableInterrupts().execute(regs, flags, bus);

        assertEquals(false, regs.isInterruptsEnabled());
    }

    @Test
    @DisplayName("DI runs for correct number of cycles, length")
    public void lengthAndCycles() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        int before = regs.getProgramCounter().read();

        assertEquals(1, DI.disableInterrupts().execute(regs, flags, bus));

        int after = regs.getProgramCounter().read();

        assertEquals(after - before, 1);
    }

}
