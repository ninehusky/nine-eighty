package memory.rom;

import memory.rom.ROM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ROMTest {
    public static final String DEBUG_ROM_PATH = "src/test/resources/debug.rom";
    ROM cart;

    @BeforeEach
    void setUp() {
        cart = new ROM(5000, new File(DEBUG_ROM_PATH));
    }

    @Test
    void getByte() {
        try {
            InputStream stream = new FileInputStream(DEBUG_ROM_PATH);
            for (int i = 0; i < 5; i++) {
                // Verify first five bytes match
                assertEquals(stream.read(), cart.getByte(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByteThrowsExceptionWithIncorrectAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.getByte(Integer.MAX_VALUE);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cart.getByte(-1);
        });
    }

    @Test
    void setByteThrowsExceptionWithValidAddress() {
        assertThrows(UnsupportedOperationException.class, () -> {
            cart.setByte(0, 3);
        });
    }

    @Test
    void setByteThrowsExceptionWithInvalidAddress() {
        assertThrows(UnsupportedOperationException.class, () -> {
            cart.setByte(Integer.MAX_VALUE, 3);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            cart.setByte(-1, 3);
        });
    }


}