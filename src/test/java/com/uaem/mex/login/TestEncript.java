package com.uaem.mex.login;

import org.junit.Test;

import com.uaem.mex.login.util.Utils;

public class TestEncript {
	
	
	@Test
	public void encrip() {
		String var = "hola";
		String encri;
		String desencri;
		try {
			encri = Utils.encriptar(var);
			System.out.println("ENCRIPTADO: " + encri);
			desencri = Utils.desencriptar(encri);
			System.out.println("DESNCRIPTADO: " + desencri);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
