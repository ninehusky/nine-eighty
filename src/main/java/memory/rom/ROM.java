package memory.rom;


import memory.AddressSpace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ROM extends AddressSpace {
    public ROM(int size, File ROMFile) {
        this.memory = new int[size];

        try {
            InputStream inputStream = new FileInputStream(ROMFile);

            byte[] gameData = new byte[(int) ROMFile.length()];

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
    public void setByte(int address, int value) {
        throw new UnsupportedOperationException("Cannot set byte in ROM!");
    }
}
