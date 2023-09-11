package com.allezon.core.domain.aggregate;

import java.io.Serializable;

public record Aggregate(String hash, long sum, long count) implements Serializable {
}
