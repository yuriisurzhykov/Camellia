package com.yuriysurzhykov.camellia.utils;

public class Utils {

    public static final String CIPHER_FILE_EXT = ".crptd";
    public static final String TEXT_FILE_EXT = ".txt";

    public static byte[][] longToByte(long D1, long D2) {
        byte[][] bytes = new byte[2][8];
        for (int i = 7; i >= 0; i--) {
            bytes[0][i] = (byte) D1;
            bytes[1][i] = (byte) D2;
            D1 >>>= 8;
            D2 >>>= 8;
        }
        return bytes;
    }
}
