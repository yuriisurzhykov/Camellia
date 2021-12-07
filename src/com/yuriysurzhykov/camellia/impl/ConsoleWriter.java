package com.yuriysurzhykov.camellia.impl;

import com.yuriysurzhykov.camellia.declaration.IByteEncoder;
import com.yuriysurzhykov.camellia.declaration.IWriter;

import java.util.Arrays;

public class ConsoleWriter implements IWriter {

    private final StringBuilder stringBuilder;
    private final IByteEncoder byteEncoder;

    public ConsoleWriter(IByteEncoder byteEncoder) {
        stringBuilder = new StringBuilder();
        this.byteEncoder = byteEncoder;
    }

    @Override
    synchronized public void write(byte[] b) {
        write(b, 0, b.length);
    }

    @Override
    synchronized public void write(byte[] b, int off, int len) {
        stringBuilder.append(byteEncoder.decode(Arrays.copyOfRange(b, off, len)));
    }

    @Override
    synchronized public void flush() {

    }

    @Override
    synchronized public void close() {
        System.out.println(stringBuilder);
    }

    public String getText() {
        return stringBuilder.toString();
    }
}
