package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MVITest {
    @Test
    @DisplayName("MVI loads register with immediate (and opcode/cycle length are correct)")
    void testMviRegisterBehavior() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        bus.write(MemoryBus.RAM_START_ADDRESS, 0xCD);
        bus.write(MemoryBus.RAM_START_ADDRESS + 1, 0xAB);
        bus.write(MemoryBus.RAM_START_ADDRESS + 2, 0xCD);

        for (Register reg : Register.values()) {
            regs.getProgramCounter().write(MemoryBus.RAM_START_ADDRESS);
            int before = regs.getProgramCounter().read();
            assertEquals(2, MVI.loadRegisterWithImmediate(reg).execute(regs, f, bus));
            int after = regs.getProgramCounter().read();
            assertEquals(2, after - before);
            assertEquals(0xAB, regs.read(reg));
        }
    }

    @Test
    @DisplayName("MVI loads memory with immediate (and opcode/cycle length) are correct")
    void testMviMemoryBehavior() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        bus.write(MemoryBus.RAM_START_ADDRESS, 0xCD);
        bus.write(MemoryBus.RAM_START_ADDRESS + 1, 0xAB); // this is what we are writing
        bus.write(MemoryBus.RAM_START_ADDRESS + 2, 0xCD);

        // write to ram start addr + 3
        regs.write(Register.H, (MemoryBus.RAM_START_ADDRESS + 3) >> 8);
        regs.write(Register.L, (MemoryBus.RAM_START_ADDRESS + 3) & 0xFF);

        regs.getProgramCounter().write(MemoryBus.RAM_START_ADDRESS);

        int before = regs.getProgramCounter().read();
        assertEquals(2, MVI.loadMemoryWithImmediate().execute(regs, f, bus));
        assertEquals(0xAB, bus.read(MemoryBus.RAM_START_ADDRESS + 3));
        int after = regs.getProgramCounter().read();
        assertEquals(2, after - before);
    }
}