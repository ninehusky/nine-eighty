package cpu.registers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistersTest {
    @Test
    @DisplayName("Registers can be written")
    void testRegistersWrite() {
        Registers regs = new Registers();
        for (Registers.Register r : Registers.Register.values()) {
            regs.write(r, 0xFF);
        }

        for (Registers.Register r : Registers.Register.values()) {
            assertEquals(0xFF, regs.read(r));
        }
    }

    @Test
    @DisplayName("Registers correctly throw exception upon overflow")
    void testRegistersExceptionUponOverflow() {
        Registers regs = new Registers();
        for (Registers.Register r : Registers.Register.values()) {
            assertThrows(IllegalArgumentException.class, () -> regs.write(r, 0x100));
        }
    }

    @Test
    @DisplayName("Stack Pointer and Program Counter correctly initialized")
    void testStackPointerProgramCounterInitialization() {
        Registers regs = new Registers();
        assertEquals(regs.getProgramCounter().read(), 0);
        assertEquals(regs.getStackPointer().read(), 0);
    }

    @Test
    @DisplayName("Registers correctly are written to")
    void testRegisterReadWrite() {
        int value = 0xAB;
        Registers regs = new Registers();
        for (Registers.Register r : Registers.Register.values()) {
            regs.write(r, value);
        }

        for (Registers.Register r : Registers.Register.values()) {
            assertEquals(regs.read(r), value);
        }
    }
}