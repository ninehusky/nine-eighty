package cpu.instructions.machinecontrol;

import cpu.flags.Flag;
import cpu.flags.Flags;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class POPTest {

    @Test
    @DisplayName("POP pops data to register pair")
    void popDataToRegisterPair() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        regs.getStackPointer().write(0xABD1);

        bus.write(0xABD1, 0xB0);
        bus.write(0xABD2, 0xBA);

        for (RegisterPair pair : RegisterPair.values()) {
            int before = regs.getProgramCounter().read();
            assertEquals(3, POP.popDataToRegisterPair(pair).execute(regs, f, bus));
            int after = regs.getProgramCounter().read();

            assertEquals(1, after - before);

            assertEquals(0xABD3, regs.getStackPointer().read());
            assertEquals(0xB0BA, regs.readPair(pair));
            regs.getStackPointer().write(0xABD1);
        }
    }

    @Test
    @DisplayName("POP pops data to accumulator and flags")
    void popDataToFlags() {
        // double check stack pointer incremented
        Registers regs = new Registers();
        Flags f = new Flags();
        MemoryBus bus = new MemoryBus(new byte[1]);

        regs.getStackPointer().write(0xABD1);

        bus.write(0xABD1, 0xB0);
        bus.write(0xABD2, 0xFF);

        int before = regs.getProgramCounter().read();
        assertEquals(3, POP.popDataToFlags().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(after - before, 1);

        assertEquals(0xABD3, regs.getStackPointer().read());

        assertEquals(0xB0, bus.read(0xABD1));

        for (Flag flag : Flag.values()) {
            assertEquals(true, f.isSet(flag));
        }
    }
}
