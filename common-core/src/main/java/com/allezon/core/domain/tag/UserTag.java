package com.allezon.core.domain.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record UserTag(String time, String cookie, String country, Device device, Action action, String origin,
					  @JsonProperty("product_info") ProductInfo product) implements Serializable {
}
