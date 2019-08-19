package com.uaem.mex.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.model.response.LoginResponse;
import com.uaem.mex.login.service.ILoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author abrahamhv
 *
 */
@Slf4j
@RestController
@RequestMapping("/usuarios/access")
public class LoginController {

	/** inyeccion del servicio. */
	@Autowired
	private ILoginService loginService;


	@PostMapping("/login")
	public ResponseEntity<Object> getAccessUser(@RequestBody LoginRequest request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		LoginResponse response = new LoginResponse();

		this.loginService.getUser(request);

		log.info("REQUEST: " + gson.toJson(request));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
