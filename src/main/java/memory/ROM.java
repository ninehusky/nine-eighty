package memory;


public abstract class ROM extends Memory {
    @Override
    public void setByte(int address, int value) {
        throw new UnsupportedOperationException("Cannot set byte in ROM!");
    }
}
