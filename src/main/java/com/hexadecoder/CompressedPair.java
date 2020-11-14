package com.hexadecoder;

import java.util.*;

public class CompressedPair {

    public final Integer decodingStrategy;
    public final String compressedValue;

    public CompressedPair(Integer decodingStrategy, String compressedValue) {
        this.decodingStrategy = decodingStrategy;
        this.compressedValue = compressedValue;
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
