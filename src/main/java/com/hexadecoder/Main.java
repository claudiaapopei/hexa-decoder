package com.hexadecoder;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<CompressedPair> compressedPairs = new CompressedDataReader().readCompressedPairs();
        List<Byte> decodedData = new CompressedDataDecoder().decode(compressedPairs);
        new DecodedDataPrinter().print(decodedData);
    }
}
