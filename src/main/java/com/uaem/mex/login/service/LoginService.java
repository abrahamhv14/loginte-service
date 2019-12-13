package com.uaem.mex.login.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uaem.mex.login.exception.AppLoginException;
import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.model.request.UserRequestDTO;
import com.uaem.mex.login.repository.ILoginRepository;
import com.uaem.mex.login.util.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author abrahamhv
 *
 */
@Slf4j
@Service
public class LoginService implements ILoginService {

	/** Objeto que obtiene los metodos para persistir la B.D. */
	@Autowired
	private ILoginRepository loginRepository;

	/**
	 * Metodo que obtiene las operaciones CRUD de los registros de la B.D.
	 *
	 * @param request Objeto con los datos de logueo del usurio.
	 * @return the user
	 */
	@Override
	public UsuarioLogin getUser(LoginRequest request) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		Optional<UsuarioLogin> response = loginRepository.findById(request.getUsername());

		if (response.isPresent()) {
			log.info("USUARIO: " + gson.toJson(response));
		}

		return response.orElse(new UsuarioLogin());
	}
	
	/**
	 * Metodo que obtiene las operaciones CRUD de los registros de la B.D.
	 *
	 * @param request Objeto con los datos de logueo del usurio.
	 * @return the user
	 * @throws AppLoginException 
	 */
	@Override
	public UsuarioLogin addUser(UserRequestDTO user) throws AppLoginException {
		
		UsuarioLogin usuario = new UsuarioLogin();
		usuario.setUsername(user.getUsername());
		usuario.setNombre(user.getNombre());
		usuario.setApellidoPaterno(user.getApellidoPaterno());
		usuario.setApellidoMaterno(user.getApellidoMaterno());
		usuario.setEstatus(1);
		usuario.setFecha(new Date());
		usuario.setSecretKey(Utils.encriptar(user.getSecretKey()));
		
		
		return loginRepository.save(usuario);
	}

}
