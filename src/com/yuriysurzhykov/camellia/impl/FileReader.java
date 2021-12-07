package com.yuriysurzhykov.camellia.impl;

import com.yuriysurzhykov.camellia.declaration.IReader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader implements IReader {

    private final BufferedInputStream inputStream;

    public FileReader(String path) throws FileNotFoundException {
        this(path, 16);
    }

    public FileReader(String path, int size) throws FileNotFoundException {
        inputStream = new BufferedInputStream(new FileInputStream(path), size);
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return inputStream.read(bytes);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
