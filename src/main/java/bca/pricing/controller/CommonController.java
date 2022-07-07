package bca.pricing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bca.pricing.model.eai.ErrorSchema;
import bca.pricing.model.message.ReturnCode;

@RestController
public class CommonController extends BaseController {
	
	@GetMapping("/unauthorized")
    public ErrorSchema error() {
        return setErrorSchema(ReturnCode.SERVICE_FORBIDDEN);
    }
}
