package memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public final class Cartridge extends ROM {
    private static final int CARTRIDGE_SIZE_IN_BYTES = 4096; // TODO: this is likely not the final size of invaders
    
    private final int[] memory;

    public Cartridge(File ROMFile) {
        this.size = CARTRIDGE_SIZE_IN_BYTES;
        this.memory = new int[CARTRIDGE_SIZE_IN_BYTES];
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
}
