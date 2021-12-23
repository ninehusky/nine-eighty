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

class MOVTest {

    @Test
    @DisplayName("MOV Register to Register correctly moves data")
    void movRegisterToRegister() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        regs.write(Register.A, 0xAB);
        regs.write(Register.B, 0xD1);

        int before = regs.getProgramCounter().read();
        assertEquals(1, MOV.movRegisterToRegister(Register.A, Register.B).execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(1, after - before);
        assertEquals(0xD1, regs.read(Register.A));
    }

    @Test
    @DisplayName("MOV Memory to Register correctly moves data")
    void movMemoryToRegister() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        bus.write(0xABD1, 0xBB);

        regs.writePair(RegisterPair.HL, 0xABD1);

        int before = regs.getProgramCounter().read();
        assertEquals(1, MOV.movMemoryToRegister(Register.A).execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(0xBB, regs.read(Register.A));
        assertEquals(1, after - before);
    }

    @Test
    @DisplayName("MOV Register to Memory correctly moves data")
    void moveRegisterToMemory() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = new MemoryBus(new byte[1]);

        regs.write(Register.A, 0xBB);

        regs.writePair(RegisterPair.HL, 0xABD1);

        int before = regs.getProgramCounter().read();
        assertEquals(1, MOV.movRegisterToMemory(Register.A).execute(regs, f, bus));
        int after = regs.getProgramCounter().read();
        assertEquals(0xBB, bus.read(0xABD1));
        assertEquals(1, after - before);
    }
}