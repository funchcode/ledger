package com.funch.ledger.util;

/**
 * Null Check Util
 */
public class NullUtils {
    private final char NULL_OF_CHARACTER = '\u0000';
    private final int NULL_OF_INTEGER = 0;

    public static <T extends Object> boolean isNullOrEmpty(T obj) {
        return obj == null;
    }

    public static boolean isEmptyOfString(String str) {
        return (str == null || str.trim().length() == 0);
    }
}
