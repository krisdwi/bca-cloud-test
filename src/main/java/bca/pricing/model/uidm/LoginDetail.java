package bca.pricing.model.uidm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginDetail {

	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("app_code")
	private String appCode;
	@JsonProperty("session_time_out")
	private String sessionTimeOut;
	@JsonProperty("login_session_id")
	private String loginSessionId;
}
