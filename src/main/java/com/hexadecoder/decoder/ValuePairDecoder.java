package com.hexadecoder.decoder;

import com.hexadecoder.*;

import java.util.*;

import static com.hexadecoder.util.StringConverter.toHexByte;

/**
 * Pairs which have 0 as key will use this class for decoding. It simply returns the value of the pair.
 */
public class ValuePairDecoder implements CompressedPairDecoder {

    @Override
    public List<Byte> decodedPair(CompressedPair pair, List<Byte> partialResult) {
        return List.of(toHexByte(pair.compressedValue));
    }
}
