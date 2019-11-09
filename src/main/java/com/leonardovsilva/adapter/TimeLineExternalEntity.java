package com.leonardovsilva.adapter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TimeLineExternalEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("event")
	private String event;
	
	@JsonProperty("timestamp")
	private String timestamp;
	
	@JsonProperty("revenue")
	private String revenue;
	
	@JsonProperty("custom_data")
	private TimeLineExternalCustomDataEntity[] customData;
}
