package com.hexadecoder.decoder;

import com.hexadecoder.*;

import java.util.*;

import static com.hexadecoder.util.StringConverter.toByte;

public class ValuePairDecoder implements CompressedPairDecoder {

    @Override
    public List<Byte> decodedPair(CompressedPair pair, List<Byte> partialResult) {
        return List.of(toByte(pair.compressedValue));
    }
}
