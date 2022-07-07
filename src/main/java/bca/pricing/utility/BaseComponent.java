package bca.pricing.utility;

import org.springframework.stereotype.Component;

import bca.pricing.model.eai.ErrorMessage;
import bca.pricing.model.eai.ErrorSchema;
import bca.pricing.model.message.ReturnMessageEnglish;
import bca.pricing.model.message.ReturnMessageIndonesia;

@Component
public class BaseComponent {
	
	public ErrorSchema setErrorSchema(String returnCode) {
		ErrorSchema errorSchema = new ErrorSchema();
		errorSchema.setErrorCode(returnCode);
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setEnglish(ReturnMessageEnglish.getMessage(returnCode));
		errorMessage.setIndonesian(ReturnMessageIndonesia.getMessage(returnCode));
		errorSchema.setErrorMessage(errorMessage);
		
		return errorSchema;
	}
	
	public ErrorSchema setErrorSchema(String returnCode, String english, String indonesia) {
		ErrorSchema errorSchema = new ErrorSchema();
		errorSchema.setErrorCode(returnCode);
		
		ErrorMessage errorMessage = new ErrorMessage(indonesia, english);
		errorSchema.setErrorMessage(errorMessage);
		
		return errorSchema;
	}
}
