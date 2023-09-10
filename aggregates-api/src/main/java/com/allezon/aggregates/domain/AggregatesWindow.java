package com.allezon.aggregates.domain;

import java.util.List;

public record AggregatesWindow(List<String> columns, List<List<String>> rows) {
}
