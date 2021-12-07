package com.yuriysurzhykov.camellia.impl;

import com.sun.org.apache.bcel.internal.Const;
import com.yuriysurzhykov.camellia.declaration.IWriter;

import java.io.*;

import static com.yuriysurzhykov.camellia.utils.Utils.CIPHER_FILE_EXT;

public class FileWriter implements IWriter {

    private final OutputStream outputStream;

    public FileWriter(String path) throws FileNotFoundException {
        outputStream = new BufferedOutputStream(new FileOutputStream(path + CIPHER_FILE_EXT));
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
