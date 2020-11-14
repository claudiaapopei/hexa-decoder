package com.hexadecoder;

import java.util.*;

import static com.hexadecoder.util.StringConverter.toHexByte;
import static com.hexadecoder.util.StringConverter.toInt;

public class CompressedPair {

    public final Integer decodingStrategy;
    public final String compressedValue;

    private CompressedPair(Integer decodingStrategy, String compressedValue) {
        this.decodingStrategy = decodingStrategy;
        this.compressedValue = compressedValue;
    }

    /**
     * Given two values, it converts them into a CompressedPair if they follow the following validations rules:
     * - they key is a positive integer
     * - when the key is 0 then the value can be converted to hexadecimal byte
     * - when the key > 0 then the value is a positive integer, but less or equal with the value of the key
     * When one of the validations fails, then a default invalid pair is returned.
     */
    public static CompressedPair pairOf(String key, String value) {
        if (!canConvertToInt(key)) {
            return invalidPair();
        }
        int decodingStrategy = toInt(key);
        if (!isValidPairInput(value, decodingStrategy)) {
            return invalidPair();
        }

        return new CompressedPair(decodingStrategy, value);
    }

    public static CompressedPair invalidPair() {
        return new CompressedPair(-1, "");
    }

    public static boolean isInvalidPair(CompressedPair pair) {
        return pair.equals(invalidPair());
    }

    private static boolean isValidPairInput(String value, int decodingStrategy) {
        return validDecodingStrategy(decodingStrategy)
                && (isValidIterativePair(value, decodingStrategy)
                || isValidValuePair(value, decodingStrategy));
    }

    private static boolean validDecodingStrategy(int decodingStrategy) {
        return decodingStrategy >= 0;
    }

    private static boolean isValidIterativePair(String iterationsValue, int decodingStrategy) {
        return decodingStrategy > 0 && canConvertToInt(iterationsValue)
                && toInt(iterationsValue) > 0 && toInt(iterationsValue) <= decodingStrategy;
    }

    private static boolean isValidValuePair(String value, int decodingStrategy) {
        return decodingStrategy == 0 && canConvertToByte(value);
    }

    private static boolean canConvertToByte(String value) {
        try {
            toHexByte(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean canConvertToInt(String value) {
        try {
            toInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompressedPair{" +
                "decodingStrategy=" + decodingStrategy +
                ", compressedValue='" + compressedValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompressedPair that = (CompressedPair) o;
        return Objects.equals(decodingStrategy, that.decodingStrategy) &&
                Objects.equals(compressedValue, that.compressedValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decodingStrategy, compressedValue);
    }
}
