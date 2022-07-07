package bca.pricing.model.uidm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggedUserRoles {

	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("role_code")
	private String roleCode;
	@JsonProperty("role_group_code")
	private String roleGroupCode;
	@JsonProperty("app_code")
	private String appCode;
	@JsonProperty("office_code")
	private String officeCode;
}
