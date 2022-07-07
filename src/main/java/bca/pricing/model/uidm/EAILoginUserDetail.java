package bca.pricing.model.uidm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import bca.pricing.model.eai.ErrorSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class EAILoginUserDetail extends ErrorSchema {

	@JsonProperty("login_detail")
	private LoginDetail loginDetail;
	@JsonProperty("logged_user_detail")
	private LoggedUserDetail loggedUserDetail;
	@JsonProperty("logged_user_roles")
	private List<LoggedUserRoles> loggedUserRoles;
	
}
