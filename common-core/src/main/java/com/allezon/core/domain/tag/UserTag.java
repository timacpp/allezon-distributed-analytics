package com.allezon.core.domain.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record UserTag(String time, String cookie, String country, Device device, Action action, String origin,
					  @JsonProperty("product_info") ProductInfo product) implements Serializable {
	public enum Device {
		PC,
		MOBILE,
		TV
	}

	public enum Action {
		VIEW,
		BUY
	}

	@JsonIgnore
	public String getTimeWithoutSeconds() {
		return time.substring(0, time.lastIndexOf(":")) + "00";
	}
}
