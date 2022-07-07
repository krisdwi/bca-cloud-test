package bca.pricing.common.aini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bca.aini.services.AINIService;
import com.fasterxml.jackson.databind.ObjectMapper;

import bca.pricing.model.aini.AiniRequestBody;
import bca.pricing.properties.AiniProperties;

@Service
public class AiniUtils {

	@Autowired
    AINIService ainiService;
	
    public Object interactWithSignatures(AiniRequestBody ainiRequestPacket) 
	throws Exception {
		String APIKey = AiniProperties.ainiApiKey;
		String APISecret = AiniProperties.ainiApiSecret;
		String clientId = AiniProperties.ainiClientId;
		String clientSecret = AiniProperties.ainiClientSecret;
		return ainiService.interactWithSignatures(clientId, clientSecret, APIKey, APISecret, 
			ainiRequestPacket.getHttpMethod(), ainiRequestPacket.getUrl(), null, ainiRequestPacket.getRequestBody(), 
			ainiRequestPacket.getAdditionalHeaders());
	}
    
	public <T> T interactWithSignatures(AiniRequestBody ainiRequestPacket, Class<T> className) 
	throws Exception {
		String APIKey = AiniProperties.ainiApiKey;
		String APISecret = AiniProperties.ainiApiSecret;
		String clientId = AiniProperties.ainiClientId;
		String clientSecret = AiniProperties.ainiClientSecret;
		
		Object responseAini = ainiService.interactWithSignatures(clientId, clientSecret, APIKey, APISecret, 
			ainiRequestPacket.getHttpMethod(), ainiRequestPacket.getUrl(), null, ainiRequestPacket.getRequestBody(), 
			ainiRequestPacket.getAdditionalHeaders());

		ObjectMapper mapper = new ObjectMapper();
		T result = (T) mapper.readValue(responseAini.toString(), className);
		
		return result;
	}
}
