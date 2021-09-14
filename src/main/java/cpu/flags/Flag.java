package cpu.flags;

public enum Flag {
    CARRY(0b00000001),
    PARITY(0b00000100),
    AUXILIARY_CARRY(0b00010000),
    ZERO(0b01000000),
    SIGN(10000000);

    private final int mask;

    Flag(int mask) {
        this.mask = mask;
    }

    // TODO: don't know if i'll need this
    public int getMask() {
        return mask;
    }
}
