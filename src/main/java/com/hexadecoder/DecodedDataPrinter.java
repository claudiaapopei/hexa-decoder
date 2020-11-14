package com.hexadecoder;

import java.io.*;
import java.util.*;

import static com.hexadecoder.CompressedDataDecoder.INVALID_ENCODING_BYTE;
import static java.util.stream.Collectors.toList;

/**
 * Printer class that when having no default invalid byte, it prints the input to the standard output, and to error
 * output otherwise. The byte is formatted to hexadecimal.
 */
public class DecodedDataPrinter {

    public static final String USE_TRIVIAL_IMPLEMENTATION = "USE_TRIVIAL_IMPLEMENTATION";

    public void print(List<Byte> decodedData) {
        PrintStream out = decodedData.contains(INVALID_ENCODING_BYTE)
                ? System.err
                : System.out;
        printData(decodedData, out);
        printReEncodedData(decodedData);
    }

    private void printData(List<Byte> decodedData, PrintStream out) {
        decodedData.forEach(decodedByte -> {
            out.print(toHex(decodedByte) + " ");
        });
    }

    private void printReEncodedData(List<Byte> decodedData) {
        decodedData = decodedData.stream()
                .filter(data -> !data.equals(INVALID_ENCODING_BYTE))
                .collect(toList());
        if (System.getenv().containsValue(USE_TRIVIAL_IMPLEMENTATION) && System.getenv(USE_TRIVIAL_IMPLEMENTATION).equals("1")) {
            encodeUsingValueStrategy(decodedData);
        } else {
            encodeUsingIterativeStrategy(decodedData);
        }
    }

    private void encodeUsingValueStrategy(List<Byte> decodedData) {
        decodedData.forEach(data ->
                System.err.printf("0 %s ", toHex(data))
        );
    }

    private void encodeUsingIterativeStrategy(List<Byte> decodedData) {
        encodeUsingValueStrategy(List.of(decodedData.get(0)));
        int i = 0;
        while (i < decodedData.size()) {
            int beforeAt = wasBeforeAt(decodedData, i, decodedData.get(i));
            if (beforeAt != -1) {
                int numberOfRepetitions = numberOfRepetitionsIn(decodedData, i, beforeAt);
                System.err.printf("%s %s ", numberOfRepetitions, numberOfRepetitions);
                i = i + numberOfRepetitions;
            } else {
                encodeUsingValueStrategy(List.of(decodedData.get(i)));
                i++;
            }
        }
    }

    private int numberOfRepetitionsIn(List<Byte> decodedData, int index, int offset) {
        int repetitions = 0;
        while (true) {
            if (index + offset < decodedData.size() && decodedData.get(index).equals(decodedData.get(index + offset))) {
                repetitions ++;
            }
            else {
                break;
            }
            index ++;
        }
        return repetitions;
    }

    private int wasBeforeAt(List<Byte> values, int index, Byte value) {
        int indexOf = values.indexOf(value);
        return indexOf < index ? indexOf : -1;
    }

    private String toHex(Byte value) {
        return String.format("%02x", value);
    }
}
