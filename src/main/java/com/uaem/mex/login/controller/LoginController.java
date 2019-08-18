package com.uaem.mex.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author abrahamhv
 *
 */
@RestController
@RequestMapping("/usuarios/access")
public class LoginController {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Gets the access user.
	 *
	 * @param request the request
	 * @return the access user
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> getAccessUser(@RequestBody Object request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		log.info("REQUEST: " + gson.toJson(request));

		return new ResponseEntity<>(request, HttpStatus.OK);

	}

}
