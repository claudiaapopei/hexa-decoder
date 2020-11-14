package com.hexadecoder.decoder;

import com.hexadecoder.*;

import java.util.*;

import static com.hexadecoder.CompressedDataDecoder.INVALID_ENCODING_BYTE;
import static com.hexadecoder.util.StringConverter.toInt;

/**
 * For pairs that have the key different than 0, then this class wil be used.
 * In order to decide the index from which the data will read, from the total size of the partial results
 * it will be subtracted the number of pairs that have to be read. The number of iterations is equal with the value
 * of the pair. When the value of the pair is greater than the number bytes resulted after parsing the previous pairs,
 * then the default invalid byte is returned since it is not a valid input value.
 */
public class IterativePairDecoder implements CompressedPairDecoder {

    @Override
    public List<Byte> decodedPair(CompressedPair pair, List<Byte> partialResult) {
        List<Byte> lastResults = new ArrayList<>();
        int offset = partialResult.size() - pair.decodingStrategy;
        int takeNoOfPreviousValues = toInt(pair.compressedValue);
        if (takeNoOfPreviousValues > partialResult.size()) {
            return List.of(INVALID_ENCODING_BYTE);
        }
        for (int i = 0; i < takeNoOfPreviousValues; i++) {
            lastResults.add(partialResult.get(i + offset));
        }
        return lastResults;
    }
}
