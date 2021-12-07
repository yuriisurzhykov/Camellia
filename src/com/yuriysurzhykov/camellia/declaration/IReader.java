package com.yuriysurzhykov.camellia.declaration;

import java.io.Closeable;
import java.io.IOException;

public interface IReader extends Closeable {

    int read(byte[] bytes) throws IOException;

    int available() throws IOException;
}
