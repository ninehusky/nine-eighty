package mmu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBusTest {
    private static final int ROM_SIZE_IN_BYTES = 8192;

    @Test
    @DisplayName("Memory bus correctly constructs with ROM buffer")
    void memoryBusConstructsWithROMBuffer() {
        byte[] buffer = new byte[ROM_SIZE_IN_BYTES];
        for (int i = 0; i < ROM_SIZE_IN_BYTES; i++) {
            buffer[i] = (byte)i;
        }

        MemoryBus bus = new MemoryBus(buffer);
        for (int i = 0; i < ROM_SIZE_IN_BYTES; i++) {
            assertEquals(i & 0xFF, bus.read(i));
        }

        // the remaining addresses are all zeros
        for (int i = MemoryBus.RAM_START_ADDRESS; i < 0x4000; i++) {
            assertEquals(0, bus.read(i));
        }
    }

    @Test
    @DisplayName("Memory bus correctly wraps around addresses")
    void memoryBusAddressesWrapAround() {
        byte[] buffer = new byte[ROM_SIZE_IN_BYTES];
        for (int i = 0; i < ROM_SIZE_IN_BYTES; i++) {
            buffer[i] = (byte)i;
        }

        MemoryBus bus = new MemoryBus(buffer);

        for (int i = 0; i < MemoryBus.RAM_START_ADDRESS; i++) {
            assertEquals(i & 0xFF, bus.read(i));
        }

        for (int i = 0x4000; i < 0x4000 + MemoryBus.RAM_START_ADDRESS; i++) {
            assertEquals(i & 0xFF, bus.read(i));
        }
    }

    @Test
    @DisplayName("Memory bus correctly defines ROM boundaries")
    void memoryBusRomBoundariesCorrect() {
        byte[] buffer = new byte[ROM_SIZE_IN_BYTES];
        for (int i = 0; i < ROM_SIZE_IN_BYTES; i++) {
            buffer[i] = (byte)i;
        }

        MemoryBus bus = new MemoryBus(buffer);
        // test ROM read only
        for (int i = 0; i < MemoryBus.RAM_START_ADDRESS; i++) {
            final int address = i;
            assertThrows(UnsupportedOperationException.class, () -> bus.write(address, 1));
        }

        // test that ROM exists at 0x4000
        assertThrows(UnsupportedOperationException.class, () -> bus.write(0x4000, 1));
        for (int i = 0x4000; i < 0x4000 + MemoryBus.RAM_START_ADDRESS; i++) {
            final int address = i;
            assertThrows(UnsupportedOperationException.class, () -> bus.write(address, 1));
        }
    }

    @Test
    @DisplayName("Memory bus contains writeable memory at correct addresses")
    void memoryBusWriteBoundaries() {
        byte[] buffer = new byte[ROM_SIZE_IN_BYTES];
        for (int i = 0; i < ROM_SIZE_IN_BYTES; i++) {
            buffer[i] = (byte)i;
        }
        MemoryBus bus = new MemoryBus(buffer);

        for (int i = MemoryBus.RAM_START_ADDRESS; i < 0x4000; i++) {
            bus.write(i, 0xAB);
        }

        // verify this has been written
        for (int i = MemoryBus.RAM_START_ADDRESS; i < 0x4000; i++) {
            assertEquals(0xAB, bus.read(i));
        }

        // verify that initial ROM has not changed
        for (int i = 0; i < MemoryBus.RAM_START_ADDRESS; i++) {
            assertEquals(buffer[i] & 0xFF, bus.read(i));
        }
    }
}