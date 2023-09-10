package com.allezon.core.domain.aggregates;

import java.io.Serializable;

public record Aggregate(String hash, long sum, long count) implements Serializable {
}
