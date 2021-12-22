package mmu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RAMTest {
    @Test
    @DisplayName("RAM correctly constructs as buffer of zeros")
    void testConstructor() {
        int capacity = 0x100;
        RAM ram = new RAM(capacity);
        for (int i = 0; i < capacity; i++) {
            assertEquals(0, ram.read(i));
        }
    }

    @Test
    @DisplayName("RAM reads do not change data")
    void testReadNoMutation() {
        int capacity = 0x100;
        RAM ram = new RAM(capacity);
        for (int i = 0; i < capacity; i++) {
            assertEquals(0, ram.read(i));
        }

        // verify that the reads did not change data
        for (int i = 0; i < capacity; i++) {
            assertEquals(0, ram.read(i));
        }
    }

    @Test
    @DisplayName("RAM writes correctly change data")
    void testRamMutatesCorrectly() {
        int capacity = 0x100;
        RAM ram = new RAM(capacity);
        for (int i = 0; i < capacity; i++) {
            ram.write(i, i);
        }

        for (int i = 0; i < capacity; i++) {
            assertEquals(i, ram.read(i));
        }
    }

    @Test
    @DisplayName("ROM throws exception upon invalid read addresses")
    void testExceptionThrownUponInvalidReadAndWrite() {
        assertThrows(IllegalArgumentException.class, () -> new RAM(0x1).read(1));
    }

    @Test
    @DisplayName("RAM throws exception on invalid write addresses")
    void testExceptionThrownUponWrite() {
        assertThrows(IllegalArgumentException.class, () -> new RAM(0x1).write(1, 0));
    }

}