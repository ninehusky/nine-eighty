package mmu;

/**
 * Represents a chunk of read-only memory, which cannot be written to or modified post-instantiation.
 */
public class ROM extends RAM {
    /**
     * Constructs a new ROM
     * @param buffer - the data to be stored in this
     */
    public ROM(byte[] buffer) {
        super(buffer.length);
        for (int i = 0; i < buffer.length; i++) {
            this.buffer[i] = (int)buffer[i] & 0xFF;
        }
    }

    /**
     * Throws an UnsupportedOperationException, as ROM is read-only
     * @throws UnsupportedOperationException in all cases, ROM is non-writeable
     */
    @Override
    public void write(int address, int value) {
        throw new UnsupportedOperationException();
    }
}
