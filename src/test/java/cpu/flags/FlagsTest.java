package cpu.flags;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlagsTest {
    @Test
    @DisplayName("Flags construct with all flags off")
    void testConstructor() {
        Flags flags = new Flags();
        for (Flag f : Flag.values()) {
            assertEquals(false, flags.isSet(f));
        }
    }

    @Test
    @DisplayName("Flags can be set individually")
    void testSetIndividual() {
        Flags flags = new Flags();
        for (Flag f : Flag.values()) {
            flags.set(f);
            assertEquals(true, flags.isSet(f));
        }
    }

    @Test
    @DisplayName("Flags can be reset")
    void testResetIndividual() {
        Flags flags = new Flags();
        for (Flag f : Flag.values()) {
            flags.set(f);
            assertEquals(true, flags.isSet(f));
            flags.reset(f);
        }

        for (Flag f : Flag.values()) {
            assertEquals(false, flags.isSet(f));
        }
    }

}