package com.uaem.mex.login.service;

import com.uaem.mex.login.exception.AppLoginException;
import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.model.request.UserRequestDTO;

/**
 * 
 * @author Abraham-H-V
 *
 */
public interface ILoginService {

	UsuarioLogin getUser(LoginRequest request);
	
	UsuarioLogin addUser(UserRequestDTO request) throws AppLoginException;

}
