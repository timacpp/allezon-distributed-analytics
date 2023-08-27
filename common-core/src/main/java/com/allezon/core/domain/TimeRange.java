package com.allezon.core.domain;

import java.time.Instant;

public record TimeRange(Instant start, Instant end) {
	public TimeRange(String timeRange) {
		this(timeRange.split("_"));
	}

	private TimeRange(String[] range) {
		this(Instant.parse(range[0]), Instant.parse(range[1]));
	}

	public boolean includes(String time) {
		Instant instant = Instant.parse(time);
		return start.isBefore(instant) && end.isAfter(instant);
	}
}
