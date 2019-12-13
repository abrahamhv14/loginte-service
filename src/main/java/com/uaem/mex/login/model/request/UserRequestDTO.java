package com.uaem.mex.login.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Abraham-H-V
 *
 */
@Data
@NoArgsConstructor
public class UserRequestDTO implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** nombre de usuario. */
	private String username;

	/** nombre del usuario. */
	private String nombre;

	/** apellido paterno del usuario. */
	private String apellidoPaterno;

	/** apellido materno del usuario. */
	private String apellidoMaterno;
	
	/** clave secrteta*/
	private String secretKey;

}
