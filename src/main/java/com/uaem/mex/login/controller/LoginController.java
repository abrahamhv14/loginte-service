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
import com.uaem.mex.login.exception.AppLoginException;
import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.model.request.UserRequestDTO;
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
	
	/** Estatus de la operacion. */
	private HttpStatus httpStatus;

	/**
	 * Método para loguear a un usuario.
	 * 
	 * @param request
	 * @return codigo de operacion
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> getAccessUser(@RequestBody LoginRequest request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		LoginResponse response = new LoginResponse();

		UsuarioLogin usuario = loginService.getUser(request);

		if (usuario.getUsername() != null) {
			log.info("***USER: " + gson.toJson(usuario));
			response.setCodeOperation("000");
			httpStatus = HttpStatus.OK;
		} else {
			response.setCodeOperation("001");
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		log.info("***RESPONSE: " + gson.toJson(response));

		return new ResponseEntity<>(response, httpStatus);

	}

	/**
	 * Método para dar de alta un usuario.
	 * 
	 * @param request
	 * @return codigo de operacion
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addUser(@RequestBody UserRequestDTO request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		LoginResponse response = new LoginResponse();

		UsuarioLogin usuario;
		try {
			usuario = loginService.addUser(request);
			if (usuario != null) {
				log.info("***USER: " + gson.toJson(usuario));
				response.setCodeOperation("000");
				httpStatus = HttpStatus.OK;
			} else {
				response.setCodeOperation("001");
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (AppLoginException e) {
			log.error("ERROR AL AGREGAR USUARIO ", e);
		}

		log.info("***RESPONSE: " + gson.toJson(response));

		return new ResponseEntity<>(response, httpStatus);

	}

}
