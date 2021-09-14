package utils;

public final class BitUtils {
    public static String hexByte(int value) {
        return String.format("%02X", value);
    }

    public static String hexWord(int value) {
        return String.format("%04X", value);
    }
}
