package com.allezon.core.domain.common;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
		return start.equals(instant) || start.isBefore(instant) && end.isAfter(instant);
	}

	public List<Instant> getMinutesInBetween() {
		Instant instant = start;
		List<Instant> minutes = new ArrayList<>();

		while (instant.isBefore(end)) {
			minutes.add(instant);
			instant = instant.plusSeconds(60);
		}

		return minutes;
	}
}
