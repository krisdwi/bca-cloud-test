package bca.pricing.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import bca.pricing.common.AppConstants;
import bca.pricing.common.aini.AiniUtils;
import bca.pricing.config.token.JWTTokenUtil;
import bca.pricing.model.aini.AiniRequestBody;
import bca.pricing.model.eai.ErrorSchema;
import bca.pricing.model.message.ReturnCode;
import bca.pricing.model.uidm.EAILoginUserDetail;
import bca.pricing.model.uidm.EAILogoutResponse;
import bca.pricing.model.user.LoginResponse;
import bca.pricing.model.user.LoginRequest;
import bca.pricing.model.user.Login;
import bca.pricing.utility.Encryptor;

@Service
public class UserService extends BaseService {
	
	Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	AiniUtils ainiUtils;
	
	@Autowired
	Encryptor encryptor;

	public LoginResponse login(LoginRequest request) {
		logger.info("Starting login user: {}", request.getUserId());
		LoginResponse response = new LoginResponse();
		Login lr = new Login();
		
		try {
			String urlAddress = "https://api.devapps.ocp.dti.co.id/uidm/general/api/PRICING-CALCULATOR/login";

			logger.info("=========== URL Address : {}", urlAddress);
			
			request.setPassword(encryptor.encrypt(request.getPassword()));
			request.setHostName("pricing");

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(request);
			logger.info("== REQ BODY: {}", json);

			AiniRequestBody ainiRequestPacket = new AiniRequestBody();
			ainiRequestPacket.setUrl(urlAddress);
			ainiRequestPacket.setHttpMethod("POST");
			ainiRequestPacket.setRequestBody(json);
			ainiRequestPacket.setNewAdditionalHeader("app-access-key", AppConstants.UIDM_APP_ACCESS_KEY);

			EAILoginUserDetail ainiResponse = ainiUtils.interactWithSignatures(ainiRequestPacket,
					EAILoginUserDetail.class);
			
			if (StringUtils.isBlank(ainiResponse.getErrorCode())) {
				// SUCCESS
				Map<String, Object> claims = new HashMap<>();
				claims.put("sess", ainiResponse.getLoginDetail().getLoginSessionId());
				String jwtToken = jwtTokenUtil.generateToken(claims, request.getUserId());

				lr.setToken(jwtToken);
				lr.setUserId(request.getUserId());
				response.setOutputSchema(lr);
				response.setErrorSchema(setErrorSchema(ReturnCode.SERVICE_SUCCESS));
			} else {
				// ERROR
				logger.error("Error login to UIDM: {} - {}", ainiResponse.getErrorCode(), ainiResponse.getErrorMessage().getIndonesian());
				throw new IllegalArgumentException("Invalid User ID or Password");
			}
			
		} catch (IllegalArgumentException ex) {
			logger.error("Error: {}", ex);
			response.setErrorSchema(setErrorSchema(ReturnCode.SERVICE_GENERAL_ERROR, "Invalid User ID or Password", "User ID atau Password salah"));
		} catch (Exception ex) {
			logger.error("Error: {}", ex);
			response.setErrorSchema(setErrorSchema(ReturnCode.SERVICE_GENERAL_ERROR));
		} finally {
			logger.info("Finishing login user: {}", request.getUserId());
		}
		
		return response;
	}
	
	public ErrorSchema logout(String userId) {
		logger.info("Starting logout user: {}", userId);
		ErrorSchema response = new ErrorSchema();
		
		try {
			String urlAddress = "https://api.devapps.ocp.dti.co.id/uidm/general/api/PRICING-CALCULATOR/logout";

			logger.info("=========== URL Address : {}", urlAddress);
			
			AiniRequestBody ainiRequestPacket = new AiniRequestBody();
			ainiRequestPacket.setUrl(urlAddress);
			ainiRequestPacket.setHttpMethod("POST");
			ainiRequestPacket.setRequestBody(String.format("{ \"user_id\": \"%s\"}", userId));
			ainiRequestPacket.setNewAdditionalHeader("app-access-key", AppConstants.UIDM_APP_ACCESS_KEY);
			
			EAILogoutResponse ainiResponse = ainiUtils.interactWithSignatures(ainiRequestPacket,
					EAILogoutResponse.class);
			
			if (StringUtils.isBlank(ainiResponse.getErrorCode())) {
				if (ainiResponse.getLogoutStatus() == 1) {
					response = setErrorSchema(ReturnCode.SERVICE_SUCCESS);
				} else {
					logger.error("Error logout to UIDM with logout status: {}", ainiResponse.getLogoutStatus());
					response = setErrorSchema(ReturnCode.SERVICE_GENERAL_ERROR);
				}
			} else {
				// ERROR
				logger.error("Error logout to UIDM: {} - {}");
				throw new Exception("Error logout to UIDM");
			}
		} catch (Exception ex) {
			logger.error("Error: {}", ex);
			response = setErrorSchema(ReturnCode.SERVICE_GENERAL_ERROR);
		} finally {
			logger.info("Finishing logout user: {}", userId);
		}
		return response;
	}
}
