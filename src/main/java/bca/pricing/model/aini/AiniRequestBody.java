package bca.pricing.model.aini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AiniRequestBody {

	@JsonProperty("url")
	private String url;
	@JsonProperty("http-method")
	private String httpMethod;
	@JsonProperty("request-body")
	private String requestBody;

	@JsonProperty("additional-headers")
	private List<HashMap<String, String>> additionalHeaders;
	
	public AiniRequestBody() {
		this.additionalHeaders = new ArrayList<>();
	}
	
	public void setNewAdditionalHeader(String key, String value) {
		HashMap<String, String> additionalHeader = new HashMap<>();
		additionalHeader.put(key, value);
		this.additionalHeaders.add(additionalHeader);
	}
}
