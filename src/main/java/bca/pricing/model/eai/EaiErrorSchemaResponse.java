package bca.pricing.model.eai;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EaiErrorSchemaResponse {
	
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
}
