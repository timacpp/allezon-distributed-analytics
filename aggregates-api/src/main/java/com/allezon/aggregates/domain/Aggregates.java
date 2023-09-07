package com.allezon.aggregates.domain;

import java.util.List;

public record Aggregates(List<String> columns, List<List<String>> rows) {
}
