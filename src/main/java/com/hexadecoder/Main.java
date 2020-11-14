package com.hexadecoder;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<CompressedPair> compressedPairs = new CompressedDataReader().readCompressedPairs();
        List<Byte> decodedData = new CompressedDataDecoder().decode(compressedPairs);
        decodedData.forEach(decodedByte -> {
            System.out.printf("%02x", decodedByte);
            System.out.print(" ");
        });
    }
}
