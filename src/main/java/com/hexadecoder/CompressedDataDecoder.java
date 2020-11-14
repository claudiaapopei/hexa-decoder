package com.hexadecoder;

import com.hexadecoder.decoder.*;

import java.util.*;

import static com.hexadecoder.CompressedPair.isInvalidPair;
import static com.hexadecoder.util.StringConverter.toByte;

public class CompressedDataDecoder {

    public static final Byte INVALID_ENCODING_BYTE = toByte("3F");
    private final List<Byte> result = new ArrayList<>();
    private final PairDecoderFactory decoderFactory = new PairDecoderFactory();

    public List<Byte> decode(List<CompressedPair> pairs) {
        pairs.forEach(pair -> result.addAll(toBytes(pair)));
        return result;
    }

    private List<Byte> toBytes(CompressedPair pair) {
        if (isInvalidPair(pair)) {
            return List.of(INVALID_ENCODING_BYTE);
        }
        CompressedPairDecoder decoder = decoderFactory.decoderFor(pair);
        return decoder.decodedPair(pair, result);
    }
}
