package bca.pricing.model.eai;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = -5949395779757002156L;

	@JsonProperty("indonesian")
	private String Indonesian;

	@JsonProperty("english")
	private String English;
	
	public ErrorMessage(String indonesia, String english) {
		super();
		Indonesian = indonesia;
		English = english;
	}

	public ErrorMessage() {
		super();
	}

}
