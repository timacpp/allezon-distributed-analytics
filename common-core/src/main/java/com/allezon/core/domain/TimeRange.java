package com.allezon.core.domain;

import java.time.Instant;

public record TimeRange(Instant start, Instant end) {
	public static final TimeRange ANY = new TimeRange(Instant.MIN, Instant.MAX);
	private static final String UTC_SUFFIX = "Z";

	public TimeRange(String timeRange) {
		this(timeRange.split("_"));
	}

	private TimeRange(String[] range) {
		this(Instant.parse(range[0] + UTC_SUFFIX), Instant.parse(range[1] + UTC_SUFFIX));
	}

	public boolean includes(Instant instant) {
		return start.isBefore(instant) && end.isAfter(instant);
	}
}
