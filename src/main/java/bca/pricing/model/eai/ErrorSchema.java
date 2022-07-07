package bca.pricing.model.eai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * Copyright (c) 2017 Bank Central Asia, PT. All Rights Reserved.
 *
 * Jun 12, 2017
 *
 * @author CHANDRA WIJAYA (u060633)
 * 
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorSchema {

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("error_message")
	private ErrorMessage errorMessage;

	public ErrorSchema() {
		super();
	}

}
