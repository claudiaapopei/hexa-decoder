package com.hexadecoder;

import java.io.*;
import java.util.*;

import static com.hexadecoder.CompressedDataDecoder.INVALID_ENCODING_BYTE;

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
