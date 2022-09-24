package cpu.instructions.machinecontrol;

import cpu.flags.Flags;
import cpu.registers.Register;
import cpu.registers.RegisterPair;
import cpu.registers.Registers;
import mmu.MemoryBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PUSHTest {
    @Test
    @DisplayName("PUSH pushes register data to stack, updates SP and PC correctly")
    void pushDataFromRegisters() {
        Registers regs = new Registers();
        Flags f = new Flags();
        MemoryBus bus = new MemoryBus(new byte[1]);

        for (RegisterPair pair : RegisterPair.values()) {
            // put placeholder data inside
            regs.getStackPointer().write(0xABD1);
            regs.writePair(pair, 0xCAFE);
            bus.write(0xABD1 - 1, 0xB0);
            bus.write(0xABD1 - 2, 0xBA);

            int before = regs.getProgramCounter().read();
            assertEquals(3, PUSH.pushDataToStack(pair).execute(regs, f, bus));
            int after = regs.getProgramCounter().read();

            assertEquals(1, after - before);

            assertEquals(0xABD1 - 2, regs.getStackPointer().read());
            assertEquals(0xCA, bus.read(0xABD1 - 1));
            assertEquals(0xFE, bus.read(0xABD1 - 2));
        }
    }

    @Test
    @DisplayName("PUSH pushes register/flag data to stack, updates SP and PC")
    void pushDataAndFlags() {
        Registers regs = new Registers();
        Flags f = new Flags();
        MemoryBus bus = new MemoryBus(new byte[1]);
        regs.getStackPointer().write(0xABD1);
        regs.write(Register.A, 0xCA);
        f.setWithMask(0xFE);

        bus.write(0xABD1 - 1, 0xB0);
        bus.write(0xABD1 - 2, 0xBA);

        int before = regs.getProgramCounter().read();
        assertEquals(3, PUSH.pushDataAndFlagsToStack().execute(regs, f, bus));
        int after = regs.getProgramCounter().read();

        assertEquals(0xABD1 - 2, regs.getStackPointer().read());
        assertEquals(1, after - before);
        assertEquals(0xCA, bus.read(0xABD1 - 1));
        assertEquals(0xFE, bus.read(0xABD1 - 2));
    }

}
