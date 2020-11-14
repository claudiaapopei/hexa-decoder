package com.hexadecoder.util;

public class StringConverter {

    public static Integer toInt(String value) {
        return Integer.valueOf(value);
    }

    public static byte toByte(String value) {
        return Integer.decode("0x" + value).byteValue();
    }
}
