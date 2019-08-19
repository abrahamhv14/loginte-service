package com.uaem.mex.login.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author abrahamhv
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** The username. */
	private String username;

	/** The secret key. */
	private String secretKey;

}
