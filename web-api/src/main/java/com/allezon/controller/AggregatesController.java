package com.allezon.controller;

import com.allezon.domain.ActionType;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregates")
public class AggregatesController {

	private enum AggregationType {
		COUNT,
		SUM_PRICE
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getAggregates(
			@RequestParam(name = "time_range") String timeRange,
			@RequestParam(name = "action") ActionType actionType,
			@RequestParam(name = "aggregates") List<AggregationType> aggregationTypes,
			@RequestParam(name = "origin", required = false) String origin,
			@RequestParam(name = "brandId", required = false) String brandId,
			@RequestParam(name = "categoryId", required = false) String categoryId) {
		return ResponseEntity.ok().build();
	}
}
