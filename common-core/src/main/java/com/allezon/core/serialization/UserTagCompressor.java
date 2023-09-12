package com.allezon.core.serialization;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

import com.allezon.core.domain.tag.UserTag;

public final class UserTagCompressor {

    private static final Logger logger = LoggerFactory.getLogger(UserTagCompressor.class);

    private UserTagCompressor() {

    }

    public static byte[] compress(UserTag userTag) {
        byte[] bytes = SerializationUtils.serialize(userTag);
        try {
            return Snappy.compress(bytes);
        } catch (IOException exception ) {
            logger.error("Failed to compress tag: {}", userTag);
            return new byte[]{};
        }
    }

    public static List<UserTag> uncompress(List<byte[]> compressedTags) {
        if (compressedTags == null) {
            return List.of();
        }
        return compressedTags.stream()
                .map(UserTagCompressor::uncompress)
                .toList();
    }

    public static UserTag uncompress(byte[] compressed) {
        try {
            byte[] uncompressed = Snappy.uncompress(compressed);
            return SerializationUtils.deserialize(uncompressed);
        } catch (IOException exception) {
            logger.error("Failed to uncompress user tag", exception);
            return null;
        }
    }
}
