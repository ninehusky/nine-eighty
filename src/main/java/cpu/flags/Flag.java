package cpu.flags;

/**
 * Represents one flag of the 8080.
 *
 * Each flag is represented by one bit in some 8-bit value that contains value of all flags.
 * This means that each flag has a corresponding mask which can be used to isolate a bit in
 * an 8-bit value.
 */

public enum Flag {
    CARRY(0b00000001),
    PARITY(0b00000100),
    ZERO(0b01000000),
    AUXILIARY_CARRY(0b00010000),
    SIGN(0b10000000);

    private final int mask;

    /**
     * Constructs a new flag.
     * @param mask - mask isolating this specific flag among 8-bit value
     */
    Flag(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }
}
