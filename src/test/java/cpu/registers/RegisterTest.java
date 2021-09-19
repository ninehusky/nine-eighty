package cpu.registers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register testReg;

    @BeforeEach
    void setUp() {
        testReg = new Register("test");
    }

    @Test
    void testRegisterConstruction() {
        assertEquals(0, testReg.getValue());
    }

    @Test
    void setValue() {
        testReg.setValue(0xFE);
        assertEquals(0xFE, testReg.getValue());
    }

    @Test
    void setValueThrowsException() {
        assertThrows(IllegalStateException.class, () -> testReg.setValue(0xFF + 1));
    }

    @Test
    void testToString() {
        assertEquals("test", testReg.toString());
    }
}