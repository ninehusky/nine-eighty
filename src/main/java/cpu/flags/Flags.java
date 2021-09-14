package cpu.flags;

public class Flags {
    private static final int WORD_MASK = 0xFFFFFFF;
    private int value;

    public int getValue() {
        return value;
    }

    /**
     *
     * @param flag the flag with which to assess
     * @return true if the flag is set, false otherwise
     */
    public boolean isSet(Flag flag) {
        int temp = value & flag.getMask();
        return temp != 0;
    }

    /**
     * Turns on the given flag.
     * @param flag
     */
    public void setFlag(Flag flag) {
        value |= flag.getMask();
    }

    /**
     * Turns off the given flag.
     * @param flag
     */
    public void resetFlag(Flag flag) {
        value &= (~flag.getMask());
    }
}
