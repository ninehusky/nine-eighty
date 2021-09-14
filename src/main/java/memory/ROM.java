package memory;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public final class ROM extends Memory {
    private final int[] memory;

    private static final int ROM_SIZE = 4096; // TODO: this is likely not the final size of invaders

    public ROM(File ROMFile) {
        this.size = ROM_SIZE;
        this.memory = new int[ROM_SIZE];
        try {
            InputStream inputStream = new FileInputStream(ROMFile);

            byte[] gameData = new byte[(int)ROMFile.length()];

            inputStream.read(gameData);

            for (int i = 0; i < gameData.length; i++) {
                memory[i] = (gameData[i] & 0xFF);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getByte(int address) {
        checkAddress(address);
        return memory[address];
    }

    @Override
    public void setByte(int address, int value) {
        throw new UnsupportedOperationException("Cannot set byte in ROM!");
    }
}
