package com.hexadecoder;

import com.hexadecoder.decoder.*;

import java.util.*;

import static com.hexadecoder.CompressedPair.isInvalidPair;
import static com.hexadecoder.util.StringConverter.toHexByte;

/**
 * Given a list of pairs, it decodes them depending on the nature of that pair.
 * The input might contain invalid pair, so for that case no decoder is used, and a default byte value is returned.
 */
public class CompressedDataDecoder {

    public static final Byte INVALID_ENCODING_BYTE = toHexByte("3F");
    private final List<Byte> result = new ArrayList<>();
    private final PairDecoderFactory decoderFactory = new PairDecoderFactory();

    public List<Byte> decode(List<CompressedPair> pairs) {
        pairs.forEach(pair ->
                result.addAll(toBytes(pair))
        );
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
