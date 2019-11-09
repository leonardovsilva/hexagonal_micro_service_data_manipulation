package com.leonardovsilva.adapter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeLineExternalCustomDataEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("key")
	private String key;
	
	@JsonProperty("value")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
