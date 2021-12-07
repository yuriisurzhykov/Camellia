package com.yuriysurzhykov.camellia.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private Log() {
    }

    private static final Logger LOGGER = Logger.getLogger("LOG");
    private static final Level error = Level.SEVERE;

    public static void e(String tag, String message, Throwable e) {
        LOGGER.log(error, tag + ": " + message, e);
    }

    public static void e(String tag, String message) {
        e(tag, message, null);
    }
}
