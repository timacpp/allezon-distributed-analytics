package com.allezon.aggregates;

import com.allezon.core.domain.Aggregates;
import com.allezon.core.domain.UserTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aggregates")
public class AggregatesController {

	@PostMapping("/aggregates")
	public ResponseEntity<Aggregates> getAggregates(
			@RequestParam("time_range") String timeRange,
			@RequestParam("action") UserTag.Action action,
			@RequestParam("aggregates") List<Aggregates.Type> aggregateTypes,
			@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "brand_id", required = false) String brandId,
			@RequestParam(value = "category_id", required = false) String categoryId,
			@RequestBody(required = false) Aggregates expectedAggregates) {
		return ResponseEntity.ok(expectedAggregates);
	}
}
