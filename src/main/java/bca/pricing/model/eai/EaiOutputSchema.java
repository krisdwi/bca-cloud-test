/**
 * Copyright (c) 2017 Bank Central Asia, PT. All Rights Reserved.
 *
 * @author : CHANDRA WIJAYA (u060633)
 * @email : chandra_wijaya@bca.co.id
 * @date : Jun 16, 2017
 * @project : maps-app
 *
 */
package bca.pricing.model.eai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper=false)
public abstract class EaiOutputSchema<OutputSchema> extends EaiErrorSchema {

	private static final long serialVersionUID = -1810247038565055481L;

	@JsonProperty("output_schema")
	private OutputSchema outputSchema;
}