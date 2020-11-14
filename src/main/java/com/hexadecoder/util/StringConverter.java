package com.hexadecoder.util;

public class StringConverter {

    public static Integer toInt(String value) {
        return Integer.valueOf(value);
        // NumberFormatException
    }

    public static byte toByte(String value) {
            byte byteValue = Integer.decode("0x" + value).byteValue();
            return byteValue;
    }
}
