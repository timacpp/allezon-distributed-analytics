package com.allezon.aggregates;

import com.allezon.aggregates.domain.Aggregates;
import com.allezon.aggregates.domain.AggregationType;
import com.allezon.core.domain.TimeRange;
import com.allezon.core.domain.UserTag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aggregates")
public class AggregatesController {
	private static final Logger logger = LoggerFactory.getLogger(AggregatesController.class);

	@Autowired
	private AggregatesService aggregatesService;

	@PostMapping("/aggregates")
	public ResponseEntity<Aggregates> getAggregates(
			@RequestParam("time_range") String timeRange,
			@RequestParam("action") UserTag.Action action,
			@RequestParam("aggregates") List<AggregationType> aggregateTypes,
			@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "brand_id", required = false) String brandId,
			@RequestParam(value = "category_id", required = false) String categoryId,
			@RequestBody(required = false) Aggregates expectedAggregates) {
		Aggregates aggregates = aggregatesService.getAggregates(TimeRange.parse(timeRange), action, aggregateTypes, origin, brandId, categoryId);

		if (!aggregates.equals(expectedAggregates)) {
            logger.error("Aggregates mismatch for timeRange={}, actual={}, expected={}",
                    timeRange, aggregates, expectedAggregates);
		}

		return ResponseEntity.ok(aggregates);
	}
}
