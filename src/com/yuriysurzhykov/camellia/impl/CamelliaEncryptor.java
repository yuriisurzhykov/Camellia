package com.yuriysurzhykov.camellia.impl;

import com.yuriysurzhykov.camellia.declaration.IEncryptor;
import com.yuriysurzhykov.camellia.declaration.IReader;
import com.yuriysurzhykov.camellia.declaration.IWriter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.yuriysurzhykov.camellia.impl.Camellia.crypt;
import static com.yuriysurzhykov.camellia.impl.Camellia.keySchedule;
import static com.yuriysurzhykov.camellia.utils.Utils.longToByte;

public class CamelliaEncryptor implements IEncryptor {

    @Override
    public boolean encrypt(String path, String key) throws IOException {
        IReader reader = new FileReader(path);
        IWriter writer = new FileWriter(path);
        return encrypt(key, reader, writer);
    }

    @Override
    public boolean encrypt(String path, String key, IReader reader) throws IOException {
        IWriter writer = new FileWriter(path);
        return encrypt(key, reader, writer);
    }

    @Override
    public boolean encrypt(String path, String key, IWriter writer) throws IOException {
        IReader reader = new FileReader(path);
        return encrypt(key, reader, writer);
    }

    @Override
    public boolean encrypt(String key, IReader reader, IWriter writer) throws IOException {
        byte[] bytes = new byte[16];
        long D1 = 0, D2 = 0;
        int i;
        long[] subkeys = keySchedule(key);
        while ((i = (reader.read(bytes))) == 16) {
            D1 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 0, 8)).getLong();
            D2 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 8, 16)).getLong();
            long[] res = crypt(D1, D2, subkeys);
            byte[][] res_b = longToByte(res[0], res[1]);
            writer.write(res_b[0]);
            writer.write(res_b[1]);
            writer.flush();
        }
        if (i > 0 && i < 7) {
            D1 = 0;
            D2 = 0;
            for (int j = 0; j < i; j++) {
                D1 <<= 8;
                D1 += bytes[j];
            }
            D1 <<= 1;
            D1 += 1;
            D1 <<= 64 - (i * 8 + 1);
        } else if (i >= 8) {
            D1 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 0, 8)).getLong();
            D2 = 0;
            for (int j = 8; j < i; j++) {
                D2 <<= 8;
                D2 += bytes[j];
            }
            D2 <<= 1;
            D2 += 1;
            D2 <<= 64 - ((i - 8) * 8L + 1);
        }
        if (i != -1) {
            long[] res = crypt(D1, D2, subkeys);
            byte[][] res_b = longToByte(res[0], res[1]);
            writer.write(res_b[0]);
            writer.write(res_b[1]);
            writer.flush();
        }
        reader.close();
        writer.close();
        return true;
    }
}
