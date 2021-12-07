package com.yuriysurzhykov.camellia.declaration;

import java.io.IOException;

public interface IEncryptor {
    boolean encrypt(String path, String key) throws IOException;

    boolean encrypt(String path, String key, IReader reader) throws IOException;

    boolean encrypt(String path, String key, IWriter writer) throws IOException;

    boolean encrypt(String key, IReader reader, IWriter writer) throws IOException;
}
