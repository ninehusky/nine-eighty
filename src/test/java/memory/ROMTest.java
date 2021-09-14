package memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ROMTest {
    private static final String DEBUG_ROM_PATH = "src/test/resources/debug.rom";
    ROM rom;

    @BeforeEach
    void setUp() {
        rom = new ROM(new File(DEBUG_ROM_PATH));
    }

    @Test
    void getByte() {
        try {
            InputStream stream = new FileInputStream(DEBUG_ROM_PATH);
            for (int i = 0; i < 5; i++) {
                // Verify first five bytes match
                assertEquals(stream.read(), rom.getByte(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByteThrowsExceptionWithIncorrectAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            rom.getByte(Integer.MAX_VALUE);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            rom.getByte(-1);
        });
    }

    @Test
    void setByteThrowsExceptionWithValidAddress() {
        assertThrows(UnsupportedOperationException.class, () -> {
            rom.setByte(0, 3);
        });
    }

    @Test
    void setByteThrowsExceptionWithInvalidAddress() {
        assertThrows(UnsupportedOperationException.class, () -> {
            rom.setByte(Integer.MAX_VALUE, 3);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            rom.setByte(-1, 3);
        });
    }


}