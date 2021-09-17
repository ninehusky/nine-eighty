package memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBusTest {
    public static final String DEBUG_ROM_PATH = "src/test/resources/debug.rom";
    MemoryBus bus;

    @BeforeEach
    void setUp() {
        bus = new MemoryBus();
    }

    @Test
    void getByteIsCorrectForROM() throws IOException {
        InputStream stream = new FileInputStream(DEBUG_ROM_PATH);
        for (int i = 0; i < 10; i++) {
            // Verify first ten bytes match
            assertEquals(stream.read(), bus.getByte(i));
        }
    }

    @Test
    void getByteIsCorrectForROMWithMirror() throws IOException {
        InputStream stream = new FileInputStream(DEBUG_ROM_PATH);
        for (int i = 0; i < 10; i++) {
            // Verify first ten bytes match
            assertEquals(stream.read(), bus.getByte(i + 0x4000));
        }
    }

    @Test
    void getByteAddressCorrectlyMirrors() {
        bus.setByte(0x4000 + MemoryBus.RAM_START_ADDRESS, 0xAB);
        assertEquals(0xAB, bus.getByte(MemoryBus.RAM_START_ADDRESS));
    }

    @Test
    void setByteRAM() {
        for (int i = MemoryBus.RAM_START_ADDRESS; i < MemoryBus.VRAM_START_ADDRESS; i++) {
            bus.setByte(i, i % 0xFF);
        }

        for (int i = MemoryBus.RAM_START_ADDRESS; i < MemoryBus.VRAM_START_ADDRESS; i++) {
            assertEquals(i % 0xFF, bus.getByte(i));
        }
    }

    @Test
    void setByteVRAM() {
        for (int i = MemoryBus.VRAM_START_ADDRESS; i < 0x4000; i++) {
            bus.setByte(i, i % 0xFF);
        }

        for (int i = MemoryBus.VRAM_START_ADDRESS; i < 0x4000; i++) {
            assertEquals(i % 0xFF, bus.getByte(i));
        }

        // verify that RAM was unaltered
        for (int i = MemoryBus.RAM_START_ADDRESS; i < MemoryBus.VRAM_START_ADDRESS; i++) {
            assertEquals(0, bus.getByte(i));
        }
    }

    @Test
    void setByteBoundaries() {
        // ROM/RAM boundary
        assertThrows(UnsupportedOperationException.class, () -> {
            bus.setByte(MemoryBus.RAM_START_ADDRESS - 1, 1);
        });

        // VRAM/ROM boundary
        assertThrows(UnsupportedOperationException.class, () -> {
            bus.setByte(0x4000, 1);
        });
    }


}