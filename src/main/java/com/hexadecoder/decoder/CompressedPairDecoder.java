package com.hexadecoder.decoder;

import com.hexadecoder.*;

import java.util.*;

public interface CompressedPairDecoder {

    List<Byte> decodedPair(CompressedPair pair, List<Byte> partialResult);
}
