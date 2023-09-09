package com.allezon.core.domain.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record ProductInfo(@JsonProperty("product_id") String productId, @JsonProperty("brand_id") String brandId,
		@JsonProperty("category_id") String categoryId, String price) implements Serializable {}
