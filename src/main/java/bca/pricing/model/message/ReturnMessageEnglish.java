package bca.pricing.model.message;

import java.util.HashMap;

public class ReturnMessageEnglish {

	private static HashMap<String, String> messageMap;
	
	static {
		messageMap = new HashMap<String, String>();
		messageMap.put(ReturnCode.SERVICE_SUCCESS, "Transaction success");
		messageMap.put(ReturnCode.SERVICE_FORBIDDEN, "Access forbidden");
		messageMap.put(ReturnCode.SERVICE_GENERAL_ERROR, "Error in process");
	}
	
	public static String getMessage(String returnMessageCode) {
		return messageMap.get(returnMessageCode);
	}
}
