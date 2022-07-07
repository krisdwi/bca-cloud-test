package bca.pricing.model.uidm;

import com.fasterxml.jackson.annotation.JsonProperty;

import bca.pricing.model.eai.ErrorSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class EAILogoutResponse extends ErrorSchema {

	@JsonProperty("logout_status")
	private int logoutStatus;
}
