package com.uaem.mex.login.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.repository.ILoginRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author abrahamhv
 *
 */
@Slf4j
@Service
public class LoginService implements ILoginService {

	/** The login repository. */
	@Autowired
	private ILoginRepository loginRepository;

	/**
	 * Gets the user.
	 *
	 * @param request the request
	 * @return the user
	 */
	@Override
	public UsuarioLogin getUser(LoginRequest request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		UsuarioLogin usuario = new UsuarioLogin();
		usuario.setUsername(request.getUsername());
		usuario.setNombre("");
		usuario.setApellidoPaterno("");
		usuario.setApellidoMaterno("");
		usuario.setEstatus(1);
		usuario.setFecha(new Date());
		usuario.setSecretKey(request.getSecretKey());

		this.loginRepository.save(usuario);

		log.info("USUARIO: " + gson.toJson(this.loginRepository.findById(request.getUsername())));

		return null;
	}

}
