package com.hexadecoder;

import com.hexadecoder.decoder.*;

import java.util.*;

public class CompressedDataDecoder {

    private final PairDecoderFactory decoderFactory = new PairDecoderFactory();

    public List<Byte> decode(List<CompressedPair> pairs) {
        List<Byte> result = new ArrayList<>();
        pairs.forEach(pair -> {
            CompressedPairDecoder decoder = decoderFactory.decoderFor(pair);
            result.addAll(decoder.decodedPair(pair, result));
        });
        return result;
    }
}
