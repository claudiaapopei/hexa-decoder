package com.hexadecoder.decoder;

import com.hexadecoder.*;

public class PairDecoderFactory {

    public CompressedPairDecoder decoderFor(CompressedPair compressedPair) {
        if (compressedPair.decodingStrategy == 0) {
            return new ValuePairDecoder();
        }
        return new IterativePairDecoder();
    }
}
