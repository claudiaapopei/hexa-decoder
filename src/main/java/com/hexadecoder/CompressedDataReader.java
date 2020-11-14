package com.hexadecoder;

import java.util.*;

import static com.hexadecoder.CompressedPair.invalidPair;
import static com.hexadecoder.CompressedPair.pairOf;

public class CompressedDataReader {

    public List<CompressedPair> readCompressedPairs() {
        System.out.println("Enter compressed data:");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        return toCompressedPairs(input);
    }

    private List<CompressedPair> toCompressedPairs(String[] input) {
        List<CompressedPair> pairs = new ArrayList<>();
        int i = 0;
        while (i < input.length - 1) {
            pairs.add(toPair(input, i));
            i = i + 2;
        }
        if (hasOddLength(input)) {
            pairs.add(invalidPair());
        }
        return pairs;
    }

    private CompressedPair toPair(String[] input, int index) {
        String decodingStrategy = input[index];
        String compressedValue = input[index + 1];
        return pairOf(decodingStrategy, compressedValue);
    }

    private boolean hasOddLength(String[] input) {
        return input.length % 2 != 0;
    }
}
