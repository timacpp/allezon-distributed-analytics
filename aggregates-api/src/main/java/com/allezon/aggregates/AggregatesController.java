package com.allezon.aggregates;

import com.allezon.aggregates.domain.QueryFilter;
import com.allezon.aggregates.domain.AggregatesWindow;
import com.allezon.aggregates.domain.AggregationOperator;
import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.tag.UserTag;

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
    public ResponseEntity<AggregatesWindow> getAggregates(
            @RequestParam("time_range") String timeRange,
            @RequestParam("action") UserTag.Action action,
            @RequestParam("aggregates") List<AggregationOperator> operators,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "brand_id", required = false) String brandId,
            @RequestParam(value = "category_id", required = false) String categoryId,
            @RequestBody(required = false) AggregatesWindow expectedAggregates) {
        QueryFilter filter = new QueryFilter(origin, brandId, categoryId);
        AggregatesWindow aggregates = aggregatesService.query(TimeRange.parse(timeRange), action, operators, filter);

        if (!aggregates.equals(expectedAggregates)) {
            logger.error("Aggregates mismatch for timeRange={}, actual={}, expected={}",
                    timeRange, aggregates, expectedAggregates);
        }

        return ResponseEntity.ok(aggregates);
    }
}
