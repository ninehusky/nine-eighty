package cpu.instructions.datatransfer;

import cpu.flags.Flags;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class LXITest {

    @Test
    @DisplayName("Loads Correct Immediate in Register pair")
    void loadWordToPair() {
        for (RegisterPair pair : RegisterPair.values()) {
            Registers regs = new Registers();
            Flags f = Mockito.mock(Flags.class);
            MemoryBus bus = Mockito.mock(MemoryBus.class);
            when(bus.read(anyInt())).thenReturn(0xAB);
            LXI.loadWordToPair(pair).execute(regs, f, bus);
            assertEquals(0xABAB, regs.readPair(pair));
        }
    }

    @Test
    @DisplayName("LXI with pair has correct cycle/opcode length")
    void loadWordToPairLength() {
        for (RegisterPair pair : RegisterPair.values()) {
            Registers regs = new Registers();
            int before = regs.getProgramCounter().read();
            Flags f = Mockito.mock(Flags.class);
            MemoryBus bus = Mockito.mock(MemoryBus.class);
            assertEquals(3, LXI.loadWordToPair(pair).execute(regs, f, bus));
            int after = regs.getProgramCounter().read();
            assertEquals(3, after - before);
        }
    }

    @Test
    @DisplayName("LXI with stack pointer has correct cycle/opcode length")
    void loadWordToStackPointerLength() {
        for (RegisterPair pair : RegisterPair.values()) {
            Registers regs = new Registers();
            Flags f = Mockito.mock(Flags.class);
            MemoryBus bus = Mockito.mock(MemoryBus.class);
            int before = regs.getProgramCounter().read();
            assertEquals(3, LXI.loadWordToStackPointer().execute(regs, f, bus));
            int after = regs.getProgramCounter().read();
            assertEquals(3, after - before);
        }
    }

    @Test
    @DisplayName("LXI loads correct immediate into stack pointer")
    void loadWordToStackPointer() {
        Registers regs = new Registers();
        Flags f = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);
        when(bus.read(anyInt())).thenReturn(0xAB);
        LXI.loadWordToStackPointer().execute(regs, f, bus);
        assertEquals(0xABAB, regs.getStackPointer().read());
    }
}