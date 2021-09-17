package memory.ram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RAMTest {
    RAM ram;

    @BeforeEach
    void setUp() {
        ram = new RAM(10);
    }

    @Test
    void ramConstructsProperly() {
        RAM newRam = new RAM(10);
        for (int i = 0; i < 10; i++) {
            assertEquals(0, newRam.getByte(i));
        }
    }

    @Test
    void testSetByte() {
        for (int i = 0; i < 10; i++) {
            ram.setByte(i, i);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(i, ram.getByte(i));
        }
    }

    @Test
    void testSetByteBoundaryValues() {
        ram.setByte(0, 0xFF);
        assertEquals(0xFF, ram.getByte(0));

    }

    @Test
    void getByteThrowsExceptionWithInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            ram.getByte(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ram.getByte(Integer.MAX_VALUE);
        });
    }

    @Test
    void setByteThrowsExceptionWithInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            ram.setByte(Integer.MAX_VALUE, 1);
        });
    }

    @Test
    void setByteThrowsExceptionWithInvalidValue() {
        assertThrows(IllegalStateException.class, () -> {
            ram.setByte(1, 0xFFF);
        });
    }
}