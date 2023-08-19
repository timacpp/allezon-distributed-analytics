package com.allezon.tags.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserTag(String time, String cookie, String country, Device device, Action action, String origin,
					  @JsonProperty("product_info") ProductInfo product) {
	public enum Device {
		PC,
		MOBILE,
		TV
	}

	public enum Action {
		VIEW,
		BUY
	}
}