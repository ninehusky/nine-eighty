package mmu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ROMTest {
    @Test
    @DisplayName("ROM constructs without errors")
    void testConstructor() {
        byte[] buf = new byte[0x100];
        new ROM(buf);
    }

    @Test
    @DisplayName("ROM constructor populates buffer correctly")
    void testConstructorPopulation() {
        byte[] buf = new byte[0x100];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte)i;
        }
        ROM rom = new ROM(buf);
        for (int i = 0; i < buf.length; i++) {
            assertEquals(buf[i], rom.read(i));
        }
    }

    @Test
    @DisplayName("ROM reads do not change data")
    void testReadNoMutation() {
        byte[] buf = new byte[0x100];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte)i;
        }
        ROM rom = new ROM(buf);
        for (int i = 0; i < buf.length; i++) {
            assertEquals(buf[i], rom.read(i));
        }

        for (int i = 0; i < buf.length; i++) {
            assertEquals(buf[i], rom.read(i));
        }
    }

    @Test
    @DisplayName("External buffer changes do not affect ROM data")
    void testBufferChangeNoMutation() {
        byte[] buf = new byte[0x100];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte)i;
        }
        ROM rom = new ROM(buf);
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte)0xAB;
        }

        for (int i = 0; i < buf.length; i++) {
            assertEquals((byte)i, rom.read(i));
        }
    }

    @Test
    @DisplayName("ROM throws exception upon invalid read addresses")
    void testExceptionThrownUponInvalidReadAndWrite() {
        byte[] buf = new byte[1]; // there is one byte of data here
        int illegalAddress = 0xF;
        assertThrows(IllegalArgumentException.class, () -> {
            new ROM(buf).read(illegalAddress);
        });
    }

    @Test
    @DisplayName("ROM throws exception on write operations")
    void testExceptionThrownUponWrite() {
        byte[] buf = new byte[1];
        assertThrows(UnsupportedOperationException.class, () -> {
            new ROM(buf).write(0, 0);
        });
    }
}