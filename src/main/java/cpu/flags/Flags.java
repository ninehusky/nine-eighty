package cpu.flags;

/**
 * Represents the state of all flags in the 8080.
 *
 * There are five flags of concern in the 8080:
 * - carry flag, set to carry out of bit 7 in arithmetic operations
 * - parity flag, set if number of bits in a result is even, odd otherwise
 * - auxiliary carry, set to carry out of bit 3 in a result (hardly used)
 * - zero flag, set if result is 0, reset otherwise
 * - sign flag, set of bit 7 of result
 */
public class Flags {
    private int value;

    /**
     * Returns true if the flag with the given mask is set.
     * @param f - flag whose status is to be tested
     * @return true if the flag with the given mask is set
     */
    public boolean isSet(Flag f) {
        return (value & f.getMask()) != 0;
    }

    /**
     * Turns the flag on.
     * @param f - the flag to be turned on
     */
    public void set(Flag f) {
        value |= f.getMask();
    }

    /**
     * Turns on the flag based on the mask.
     * @param mask - the mask corresponding to the state of each of the five bits
     * @throws IllegalArgumentException if the given mask is unable to be represented by the 8080,
     * i.e., it is over 8 bits
     */
    public void setWithMask(int mask) {
        if (mask != (mask & 0xFF)) {
            throw new IllegalArgumentException();
        }
        this.value = mask;
    }


    /**
     * Turns the flag off.
     * @param f - the flag to be turned off
     */
    public void reset(Flag f) {
        value &= (~f.getMask());
    }
}