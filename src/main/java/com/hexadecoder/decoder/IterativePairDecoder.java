package com.hexadecoder.decoder;

import com.hexadecoder.*;

import java.util.*;

import static com.hexadecoder.util.StringConverter.toInt;

public class IterativePairDecoder implements CompressedPairDecoder {

    @Override
    public List<Byte> decodedPair(CompressedPair pair, List<Byte> partialResult) {
        List<Byte> lastResults = new ArrayList<>();
        Integer offset = partialResult.size() - pair.decodingStrategy;
        int takeNoOfPreviousValues = toInt(pair.compressedValue);
        for (int i = 0; i < takeNoOfPreviousValues; i++) {
            lastResults.add(partialResult.get(i + offset));
        }
        return lastResults;
    }
}
