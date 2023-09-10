package com.allezon.aggregates.domain;

public record QueryFilter(String origin, String brandId, String categoryId) {
    public String toKey() {
        return (origin != null ? origin : "null") +
                ":" +
                (brandId != null ? brandId : "null") +
                ":" +
                (categoryId != null ? categoryId : "null");
    }
}
