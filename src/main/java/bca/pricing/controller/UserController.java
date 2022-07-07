package bca.pricing.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bca.pricing.model.eai.ErrorSchema;
import bca.pricing.model.user.LoginRequest;
import bca.pricing.model.user.LoginResponse;
import bca.pricing.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	LoginResponse login(@RequestBody LoginRequest request) {
		logger.info("Starting controller login");
		return userService.login(request);
	}
	
	@PostMapping("/logout")
	ErrorSchema logout() {
		logger.info("Starting controller logout");
		return userService.logout(MDC.get("userid"));
	}
}
