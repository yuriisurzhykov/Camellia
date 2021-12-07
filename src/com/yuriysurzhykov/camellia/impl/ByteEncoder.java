package com.yuriysurzhykov.camellia.impl;

import com.yuriysurzhykov.camellia.declaration.IByteEncoder;

import java.nio.charset.StandardCharsets;

public class ByteEncoder implements IByteEncoder {
    @Override
    public String decode(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
