import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class OpcodeTest {
    @Test
    void increment() {
        Register r = Mockito.mock(Register.class);
        when(r.getValue()).thenReturn(3);

        Opcode code = new Opcode();
        assertEquals(code.increment(r), 4);
    }
}