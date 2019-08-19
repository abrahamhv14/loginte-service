package com.uaem.mex.login.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "usuarios_login")
public class UsuarioLogin implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/** nombre de usuario. */
	@Id
	private String username;

	/** nombre del usuario. */
	private String nombre;

	/** apellido paterno del usuario. */
	private String apellidoPaterno;

	/** apellido materno del usuario. */
	private String apellidoMaterno;

	/** fecha en la que se da de alta el usuario. */
	private Date fecha;

	/** estatus en el que se encuantra el usuario inactivo o activo. */
	private Integer estatus;

	/** clave secreta usuario. */
	private String secretKey;

}
