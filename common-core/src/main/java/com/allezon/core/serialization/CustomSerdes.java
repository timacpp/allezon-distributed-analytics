package com.allezon.core.serialization;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.allezon.core.domain.tag.UserTag;

public final class CustomSerdes {

    private CustomSerdes() {

    }

    public static class UserTagSerde extends Serdes.WrapperSerde<UserTag> {
        public UserTagSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(UserTag.class));
        }
    }
}
