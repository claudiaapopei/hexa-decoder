package com.hexadecoder.decoder;

import com.hexadecoder.*;

/**
 * Factory class that depending on the key of a pair decided the right decoder class for that input pair.
 */
public class PairDecoderFactory {

    public CompressedPairDecoder decoderFor(CompressedPair compressedPair) {
        if (compressedPair.decodingStrategy == 0) {
            return new ValuePairDecoder();
        }
        return new IterativePairDecoder();
    }
}
