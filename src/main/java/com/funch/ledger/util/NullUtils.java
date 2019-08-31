package com.funch.ledger.util;

/**
 * Null Check Util
 */
public class NullUtils {
    private final char NULL_OF_CHARACTER = '\u0000';
    private final int NULL_OF_INTEGER = 0;

    public static <T extends Object> boolean isNull(T obj) {
        return obj == null;
    }

    public static boolean isEmptyOfChar(char str) {
        return '\u0000' == str;
    }

    public static boolean isEmptyOfString(String str) {
        return (str == null || str.trim().length() == 0);
    }

}
