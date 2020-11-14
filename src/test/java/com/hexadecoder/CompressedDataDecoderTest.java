package com.hexadecoder;

import org.junit.jupiter.api.*;

import java.util.*;

import static com.hexadecoder.CompressedDataDecoder.INVALID_ENCODING_BYTE;
import static com.hexadecoder.CompressedPair.invalidPair;
import static com.hexadecoder.CompressedPair.pairOf;
import static com.hexadecoder.util.StringConverter.toHexByte;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Decoder should ")
public class CompressedDataDecoderTest {

    private final CompressedDataDecoder decoder = new CompressedDataDecoder();

    @Test
    @DisplayName("return the byte value when the decoding strategy is 0")
    void decodingValuePair() {
        String value = "62";
        List<Byte> decodedBytes = decoder.decode(List.of(pairOf("0", value)));

        assertEquals(1, decodedBytes.size());
        assertEquals(toHexByte(value), decodedBytes.get(0));
    }

    @Test
    @DisplayName("return default error byte when input contains only pair with decoding strategy 1")
    void onlyIterativeDecodingStrategy() {
        String value = "62";
        List<Byte> decodedBytes = decoder.decode(List.of(pairOf("1", value)));

        assertEquals(1, decodedBytes.size());
        assertEquals(INVALID_ENCODING_BYTE, decodedBytes.get(0));
    }

    @Test
    @DisplayName("return default error byte when input contains only invalid pair")
    void invalidPairInput() {
        List<Byte> decodedBytes = decoder.decode(List.of(invalidPair()));

        assertEquals(1, decodedBytes.size());
        assertEquals(INVALID_ENCODING_BYTE, decodedBytes.get(0));
    }

    @Test
    @DisplayName("return default error byte and decoded values when input contains invalid pair followed by valid ones")
    void invalidPairInputFollowedByValidOnes() {
        String value = "20";
        List<Byte> decodedBytes = decoder.decode(List.of(invalidPair(), pairOf("0", value)));

        assertEquals(2, decodedBytes.size());
        assertEquals(INVALID_ENCODING_BYTE, decodedBytes.get(0));
        assertEquals(toHexByte(value), decodedBytes.get(1));
    }

    @Test
    @DisplayName("return the first value byte twice when having two pair: first with 0 and second with 1 decoding strategy")
    void byteValueTwice() {
        String value = "62";
        List<Byte> decodedBytes = decoder.decode(List.of(pairOf("0", value), pairOf("1", "1")));

        assertEquals(2, decodedBytes.size());
        assertEquals(toHexByte(value), decodedBytes.get(0));
        assertEquals(toHexByte(value), decodedBytes.get(1));
    }

    @Test
    @DisplayName("return bytes that are not default error byte when having valid input with both decoding strategies")
    void noDefaultErrorByte() {
        List<Byte> decodedBytes = decoder.decode(List.of(pairOf("0", "61"), pairOf("1", "1"), pairOf("0", "62"),
                pairOf("3", "2"), pairOf("3", "3")));

        assertFalse(decodedBytes.contains(INVALID_ENCODING_BYTE));
        assertEquals(toBytes(List.of(61, 61, 62, 61, 61, 62, 61, 61)), decodedBytes);
    }

    private List<Byte> toBytes(List<Integer> values) {
        return values.stream()
                .map(value -> toHexByte(value.toString()))
                .collect(toList());
    }
}
