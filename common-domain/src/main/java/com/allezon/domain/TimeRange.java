package com.allezon.domain;

import java.time.Instant;

public record TimeRange(Instant start, Instant end) {

	public static final TimeRange ANY = new TimeRange(Instant.MIN, Instant.MAX);

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

	public boolean notIncludes(String time) {
		return !includes(time);
	}
}