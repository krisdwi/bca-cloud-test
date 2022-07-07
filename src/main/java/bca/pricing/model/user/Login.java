package bca.pricing.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Login {

	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("token")
	private String token;
}
