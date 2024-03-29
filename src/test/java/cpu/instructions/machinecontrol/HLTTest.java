package cpu.instructions.machinecontrol;

import cpu.CPU;
import cpu.CPUState;
import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HLTTest {
    @Test
    @DisplayName("HLT correctly shifts CPUState")
    void testCPUStateShift() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        assertEquals(CPUState.RUNNING, regs.getCPUState());

        HLT.haltInstruction().execute(regs, flags, bus);

        assertEquals(CPUState.HALTED, regs.getCPUState());
    }

    @Test
    @DisplayName("HLT correctly increments pc, correct cycles")
    void testHltLength() {
        Registers regs = new Registers();
        Flags flags = Mockito.mock(Flags.class);
        MemoryBus bus = Mockito.mock(MemoryBus.class);

        CPU cpu = new CPU(regs, flags, bus);

        int before = regs.getProgramCounter().read();
        assertEquals(1, HLT.haltInstruction().execute(regs, flags, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(1, after - before);
    }
}
