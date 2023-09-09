package com.allezon.core.domain.statistics;

import java.io.Serializable;

public record Aggregate(String hash, long sum, long count) implements Serializable {
}
