import cpu.CPU;
import cpu.flags.Flags;
import cpu.registers.Registers;
import mmu.MemoryBus;

import java.io.IOException;
import java.io.InputStream;

public class SpaceInvaders {
    public static void main(String[] args) throws IOException {
        // TODO: command line args
        InputStream inputStream = SpaceInvaders.class.getClassLoader().getResourceAsStream("debug.rom");
        byte[] buffer = inputStream.readAllBytes();

        Registers regs = new Registers();
        Flags flags = new Flags();
        MemoryBus bus = new MemoryBus(buffer);

        CPU cpu = new CPU(regs, flags, bus);

        for (int i = 0; i < 16; i++) {
            cpu.execute();
        }
    }
}
