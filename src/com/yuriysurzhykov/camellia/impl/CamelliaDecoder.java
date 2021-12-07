package com.yuriysurzhykov.camellia.impl;

import com.sun.istack.internal.NotNull;
import com.yuriysurzhykov.camellia.declaration.IDecoder;
import com.yuriysurzhykov.camellia.declaration.IReader;
import com.yuriysurzhykov.camellia.declaration.IWriter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.yuriysurzhykov.camellia.impl.Camellia.crypt;
import static com.yuriysurzhykov.camellia.impl.Camellia.keySchedule;
import static com.yuriysurzhykov.camellia.utils.Utils.longToByte;

public class CamelliaDecoder implements IDecoder {

    private static final String TAG = CamelliaDecoder.class.getSimpleName();

    @Override
    public boolean decrypt(String path, String key) throws IOException {
        IReader reader = new FileReader(path);
        IWriter writer = new FileWriter(path.substring(0, path.lastIndexOf(".")));
        return decrypt(key, reader, writer);
    }

    @Override
    public boolean decrypt(String path, String key, IWriter writer) throws IOException {
        IReader reader = new FileReader(path);
        return decrypt(key, reader, writer);
    }

    @Override
    public boolean decrypt(String path, String key, IReader reader) throws IOException {
        IWriter writer = new FileWriter(path.substring(0, path.lastIndexOf(".")));
        return decrypt(key, reader, writer);
    }

    @Override
    public boolean decrypt(String key, IReader reader, IWriter writer) throws IOException {
        byte[] bytes = new byte[16];
        long D1 = 0, D2 = 0;
        int i;
        long[] subkeys = keySchedule(key);
        subkeys = subkeys.length == 26 ? transformKeys128(subkeys) : transformKeys192_256(subkeys);
        while ((i = (reader.read(bytes))) == 16) {
            D1 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 0, 8)).getLong();
            D2 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 8, 16)).getLong();
            long[] res = crypt(D1, D2, subkeys);
            byte[][] res_b = longToByte(res[0], res[1]);
            if (reader.available() == 0) {
                int counter = countExtraBytes(res);
                if (counter <= 8) {
                    writer.write(res_b[0]);
                    writer.write(res_b[1], 0, 8 - counter);
                } else {
                    writer.write(res_b[0], 0, 16 - counter);
                }
            } else {
                writer.write(res_b[0]);
                writer.write(res_b[1]);
            }
            writer.flush();
        }
        reader.close();
        writer.close();
        return true;
    }

    @NotNull
    private long[] transformKeys128(@NotNull long[] subkeys) {
        long[] new_subkeys = new long[subkeys.length];
        new_subkeys[0] = subkeys[24];
        new_subkeys[1] = subkeys[25];
        new_subkeys[24] = subkeys[0];
        new_subkeys[25] = subkeys[1];
        for (int i = 2; i <= 12; i++) {
            new_subkeys[i] = subkeys[25 - i];
            new_subkeys[25 - i] = subkeys[i];
        }
        return new_subkeys;
    }

    @NotNull
    private long[] transformKeys192_256(@NotNull long[] subkeys) {
        long[] new_subkeys = new long[subkeys.length];
        new_subkeys[0] = subkeys[32];
        new_subkeys[1] = subkeys[33];
        new_subkeys[32] = subkeys[0];
        new_subkeys[33] = subkeys[1];
        for (int i = 2; i <= 16; i++) {
            new_subkeys[i] = subkeys[33 - i];
            new_subkeys[33 - i] = subkeys[i];
        }
        return new_subkeys;
    }

    private int countExtraBytes(@NotNull long[] res) {
        int counter = 0, index = 1;
        long mask = 0xFFL, comp = 0x80L;
        for (int i = 0; i < 16; i++) {
            if (i == 8) {
                mask = 0xFFL;
                comp = 0x80L;
                index = 0;
            }
            if ((res[index] & mask) == 0) {
                counter++;
                mask <<= 8;
                comp <<= 8;
            } else if ((res[index] & mask) == comp) {
                return ++counter;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
