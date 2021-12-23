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

class LDAXTest {
    @Test
    @DisplayName("LDAX loads accumulator with proper byte at register address")
    void loadAccumulator() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);
        bus.write(0xABD1, 0xCD);
        for (RegisterPair pair : RegisterPair.values()) {
            // put the address into the pair
            regs.writePair(pair, 0xABD1);

            int before = regs.getProgramCounter().read();
            assertEquals(2, LDAX.loadAccumulator(pair).execute(regs, flags, bus));
            assertEquals(0xCD, regs.read(Register.A));

            // reset value of register
            regs.write(Register.A, 0);
            int after = regs.getProgramCounter().read();
            assertEquals(1, after - before);
        }
    }
}