package com.allezon.core.domain;

import java.util.List;

public record Aggregates(List<String> columns, List<List<String>> rows) {
	public enum Type {
		SUM, COUNT
	}
}