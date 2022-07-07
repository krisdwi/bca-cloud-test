package bca.pricing.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {

	@JsonProperty("user_id")
	private String userId;
	private String password;
	@JsonProperty("host_name")
	private String hostName;
}
