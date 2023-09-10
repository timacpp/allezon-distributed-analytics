package com.allezon.aggregates;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import com.allezon.core.dao.AggregatesDao;
import com.allezon.core.domain.aggregates.Aggregate;
import com.allezon.aggregates.domain.AggregatesWindow;
import com.allezon.aggregates.domain.QueryFilter;
import com.allezon.aggregates.domain.AggregationOperator;
import com.allezon.core.domain.common.TimeRange;
import com.allezon.core.domain.tag.UserTag;

@Component
public class AggregatesService {

    @Autowired
    private AggregatesDao aggregatesDao;

    public AggregatesWindow query(TimeRange timeRange, UserTag.Action action, List<AggregationOperator> operators, QueryFilter filter) {
        List<List<String>> rows = createRows(timeRange, action, operators, filter);
        List<String> columns = createColumns(operators, filter);
        return new AggregatesWindow(columns, rows);
    }

    private List<String> createColumns(List<AggregationOperator> operators, QueryFilter filter) {
        List<String> columns = new ArrayList<>();

        columns.add("1m_bucket");
        columns.add("action");
        if (filter.origin() != null) {
            columns.add("origin");
        }
        if (filter.brandId() != null) {
            columns.add("brand_id");
        }
        if (filter.categoryId() != null) {
            columns.add("category_id");
        }
        operators.forEach(operator -> columns.add(operator.toString().toLowerCase()));

        return columns;
    }

    private List<List<String>> createRows(TimeRange timeRange, UserTag.Action action, List<AggregationOperator> operators, QueryFilter filter) {
        List<Instant> buckets = timeRange.getMinutesInBetween();
        List<String> keys = buckets.stream()
                .map(minute -> minute.toString() + ":" + action + ":" + filter.toKey())
                .toList();

        return StreamUtils.zip(buckets.stream(), aggregatesDao.batchGet(keys).stream(), Pair::of)
                .map(pair -> createRow(pair.getFirst(), pair.getSecond(), action, operators, filter))
                .toList();
    }

    private List<String> createRow(Instant bucket, Aggregate aggregate, UserTag.Action action, List<AggregationOperator> operators, QueryFilter filter) {
        List<String> row = new ArrayList<>();

        row.add(bucket.toString());
        row.add(action.toString());
        if (filter.origin() != null) {
            row.add(filter.origin());
        }
        if (filter.brandId() != null) {
            row.add(filter.brandId());
        }
        if (filter.categoryId() != null) {
            row.add(filter.categoryId());
        }
        for (AggregationOperator operator : operators) {
            if (operator == AggregationOperator.COUNT) {
                row.add(String.valueOf(aggregate.count()));
            } else {
                row.add(String.valueOf(aggregate.sum()));
            }
        }

        return row;
    }
}
