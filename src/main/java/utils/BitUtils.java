package utils;

public final class BitUtils {
    public static final int BYTE_MASK = 0xFF;
    public static final int WORD_MASK = 0xFFFF;

    public static String hexByte(int value) {
        return String.format("%02X", value);
    }

    public static String hexWord(int value) {
        return String.format("%04X", value);
    }

    public static void checkByte(int value) {
        if ((value & BYTE_MASK) != value) {
            throw new IllegalStateException("Cannot store value " + hexWord(value) + " as byte.");
        }
    }

    public static void checkWord(int value) {
        if ((value & WORD_MASK) != value) {
            throw new IllegalStateException("Cannot store value " + hexWord(value) + " as word.");
        }
    }
}
