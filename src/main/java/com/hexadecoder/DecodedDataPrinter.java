package com.hexadecoder;

import java.io.*;
import java.util.*;

import static com.hexadecoder.CompressedDataDecoder.INVALID_ENCODING_BYTE;

/**
 * Printer class that when having no default invalid byte, it prints the input to the standard output, and to error
 * output otherwise. The byte is formatted to hexadecimal.
 */
public class DecodedDataPrinter {

    public void print(List<Byte> decodedData) {
        PrintStream out = decodedData.contains(INVALID_ENCODING_BYTE)
                ? System.err
                : System.out;
        printData(decodedData, out);
    }

    private void printData(List<Byte> decodedData, PrintStream out) {
        decodedData.forEach(decodedByte -> {
            out.printf("%02x", decodedByte);
            out.print(" ");
        });
    }
}
