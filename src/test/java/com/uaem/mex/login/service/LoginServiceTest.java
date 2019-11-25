package com.uaem.mex.login.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.uaem.mex.login.model.entity.UsuarioLogin;
import com.uaem.mex.login.model.request.LoginRequest;
import com.uaem.mex.login.repository.ILoginRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	
	@Mock
	private ILoginRepository loginRepository;
	
	@InjectMocks
	private LoginService loginService;
	
	private LoginRequest request;

	@Before
	public void setUp() {
		UsuarioLogin userLogin = new UsuarioLogin();
		Optional<UsuarioLogin> value = Optional.of(userLogin);
		MockitoAnnotations.initMocks(this);
		loginService = new LoginService();
		request = new LoginRequest();
		request.setUsername("AHV");
		request.setSecretKey("mexico01");
		
		doReturn(value).when(loginRepository).findById(request.getUsername());
		
		ReflectionTestUtils.setField(
	            loginService,
	            "loginRepository",
	            loginRepository);

	}

	@Test
	public void verifyTest() {
		
		UsuarioLogin userLogin = loginService.getUser(request);
		assertNotNull(userLogin);
	}

}
