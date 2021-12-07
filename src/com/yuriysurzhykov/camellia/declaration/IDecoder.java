package com.yuriysurzhykov.camellia.declaration;

import java.io.IOException;

public interface IDecoder {
    boolean decrypt(String path, String key) throws IOException;

    boolean decrypt(String path, String key, IWriter writer) throws IOException;

    boolean decrypt(String path, String key, IReader reader) throws IOException;

    boolean decrypt(String key, IReader reader, IWriter writer) throws IOException;
}
