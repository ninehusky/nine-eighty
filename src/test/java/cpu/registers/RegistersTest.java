package cpu.registers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistersTest {
    @Test
    @DisplayName("Registers can be written")
    void testRegistersWrite() {
        Registers regs = new Registers();
        for (Register r : Register.values()) {
            regs.write(r, 0xFF);
        }

        for (Register r : Register.values()) {
            assertEquals(0xFF, regs.read(r));
        }
    }

    @Test
    @DisplayName("Register Pairs can be written")
    void testRegisterPairWrite() {
        Registers regs = new Registers();
        for (RegisterPair pair : RegisterPair.values()) {
            regs.writePair(pair, 0xABD1);
            assertEquals(0xABD1, regs.readPair(pair));
            switch (pair) {
                case BC -> {
                    assertEquals(0xAB, regs.read(Register.B));
                    assertEquals(0xD1, regs.read(Register.C));
                }
                case DE -> {
                    assertEquals(0xAB, regs.read(Register.D));
                    assertEquals(0xD1, regs.read(Register.E));
                }
                default -> {
                    assertEquals(0xAB, regs.read(Register.H));
                    assertEquals(0xD1, regs.read(Register.L));
                }
            }
        }
        // TODO(acheung8): test other registers unaffected?
    }

    @Test
    @DisplayName("Register Pair writes through exception if given illegal value")
    void testRegisterPairWriteException() {
        Registers regs = new Registers();
        int illegalValue = 0xFFFF + 1;
        for (RegisterPair pair : RegisterPair.values()) {
            assertThrows(IllegalArgumentException.class, () -> regs.writePair(pair, illegalValue));
        }
    }

    @Test
    @DisplayName("Registers correctly throw exception upon overflow")
    void testRegistersExceptionUponOverflow() {
        Registers regs = new Registers();
        for (Register r : Register.values()) {
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
    @DisplayName("Stack Pointer and Program Counter can be correctly modified upon write")
    void testStackPointerProgramCounterWrite() {
        Registers regs = new Registers();
        int value = 0xAB;
        regs.getProgramCounter().write(value);
        regs.getStackPointer().write(value);

        assertEquals(regs.getProgramCounter().read(), value);
        assertEquals(regs.getStackPointer().read(), value);
    }

    @Test
    @DisplayName("Stack Pointer and Program Counter throw exceptions if set to illegal address")
    void testStackPointerProgramCounterExceptionWhenGivenIllegalAddress() {
        Registers regs = new Registers();
        // illegal value, longer than 16 bits
        int value = 0xAB + 0xFFFF;
        assertThrows(IllegalArgumentException.class, () -> regs.getStackPointer().write(value));
        assertThrows(IllegalArgumentException.class, () -> regs.getProgramCounter().write(value));
    }


    @Test
    @DisplayName("Stack Pointer and Program Counter do not throw exception if given 'out of bounds' 16-bit address")
    void testStackPointerProgramCounterOutOfBoundsWrite() {
        Registers regs = new Registers();
        // legal value, exactly 16 bits (although technically out of bounds for Memory Bus without wraparound)
        int value = 0xFFFF;
        regs.getStackPointer().write(value);
        regs.getProgramCounter().write(value);

    }

    @Test
    @DisplayName("Registers correctly write")
    void testRegisterReadWrite() {
        int value = 0xAB;
        Registers regs = new Registers();
        for (Register r : Register.values()) {
            regs.write(r, value);
        }

        for (Register r : Register.values()) {
            assertEquals(regs.read(r), value);
        }
    }
}