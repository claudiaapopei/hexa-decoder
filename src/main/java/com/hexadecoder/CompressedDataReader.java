package com.hexadecoder;

import java.util.*;

import static com.hexadecoder.util.StringConverter.toInt;

public class CompressedDataReader {

    public List<CompressedPair> readCompressedPairs() {
        List<CompressedPair> pairs = new ArrayList<>();
        System.out.println("Enter compressed data:");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int i = 0;
        while(i < input.length - 1) {
            Integer decodingStrategy = toInt(input[i]);
            String compressedValue = input[i + 1];
            pairs.add(new CompressedPair(decodingStrategy, compressedValue));
            i = i +2;
        }
        return pairs;
    }

}
