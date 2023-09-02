package com.allezon.core.domain;

import java.time.Instant;

public record TimeRange(Instant start, Instant end) {
	public static final TimeRange ANY = new TimeRange(Instant.MIN, Instant.MAX);
	private static final String UTC_SUFFIX = "Z";

	public static TimeRange parse(String timeRange) {
		String[] range = timeRange.split("_");
		return new TimeRange(range[0] + UTC_SUFFIX, range[1] + UTC_SUFFIX);
	}

	private TimeRange(String start, String end) {
		this(Instant.parse(start), Instant.parse(end));
	}

	public boolean includes(Instant instant) {
		return start.isBefore(instant) && end.isAfter(instant);
	}
}
