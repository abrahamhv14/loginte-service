package com.uaem.mex.login.service;

import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;

public interface ILoginService {

	UsuarioLogin getUser(LoginRequest request);

}
