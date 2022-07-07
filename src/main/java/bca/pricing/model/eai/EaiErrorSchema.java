package bca.pricing.model.eai;

import java.io.Serializable;

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
public abstract class EaiErrorSchema implements Serializable {
	private static final long serialVersionUID = 4474072973596170026L;

	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
}
