package com.yuriysurzhykov.camellia.declaration;

import java.io.Closeable;
import java.io.IOException;

public interface IWriter extends Closeable {
    void write(byte[] b) throws IOException;
    void write(byte[] b, int off, int len) throws IOException;
    void flush() throws IOException;
}
