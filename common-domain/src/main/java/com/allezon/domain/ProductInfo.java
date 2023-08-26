package com.allezon.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductInfo(@JsonProperty("product_id") String productId, @JsonProperty("brand_id") String brandId,
		@JsonProperty("category_id") String categoryId, String price) {}