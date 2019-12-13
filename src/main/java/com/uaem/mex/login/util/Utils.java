package com.uaem.mex.login.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.uaem.mex.login.exception.AppLoginException;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase Utileria con métodos estaticos para su uso en la capa de negocio.
 * 
 * @author abrahamhv
 *
 */
@Slf4j
public final class Utils {

	/**
	 * Nombre del esquema de cifrado que será utilizado
	 */
	private static final String AES = "AES";

	/**
	 * Nombre de la función hash que es utilizada
	 */
	private static final String SHA1 = "SHA-1";

	/**
	 * Llave para encriptar
	 */
	private static final String KEY_CLV = "!*?MySuperSecretKe&&S@nt@nderT@p!*#";

	/**
	 * Instantiates a new utils.
	 */
	private Utils() {

	}

	/**
	 * Gets the format date.
	 *
	 * @param date the date
	 * @return the format date
	 */
	public static String getFormatDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("HH:mm:ss dd/MM/yyyy");

		return dateFormat.format(date);

	}

	/**
	 * Gets the time.
	 *
	 * @param date the date
	 * @return the time
	 */
	public static String getTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime());
	}

	/**
	 * Parses the fecha.
	 *
	 * @param fecha the fecha
	 * @return the date
	 */
	public static Date parseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			log.error("***Error al parsear fecha***");
		}
		return fechaDate;
	}

	/**
	 * Encripta una cadena de texto con la clave dada, utilizando el Algoritmo AES
	 * 
	 * @param textoPlano cadena de texto plano que se desea encriptar
	 * @return String cadena de texto encriptada
	 * @throws AppLoginException 
	 */
	public static String encriptar(String textoPlano) throws AppLoginException {
		String claveEncriptada = null;

		SecretKeySpec sks;
		try {
			sks = getSecretKey();
			claveEncriptada = UtilsAes.encrypt(textoPlano, sks);
		} catch (Exception e) {
			throw new AppLoginException(e.getMessage(), e);
		}

		return claveEncriptada;
	}

	/**
	 * Desencripta una cadena de texto con la clave swap, utilizando el Algoritmo
	 * AES
	 * 
	 * @param textoEncriptado cadena de texto encriptado que se desea desencriptar
	 * @return String cadena de texto desencriptada
	 * @throws AppLoginException
	 */
	public static String desencriptar(String textoEncriptado) throws AppLoginException {
		try {
			SecretKeySpec sks = getSecretKey();

			return UtilsAes.decrypt(textoEncriptado, sks);
		} catch (Exception e) {
			throw new AppLoginException(e.getMessage(), e);
		}
	}

	/**
	 * Genera una llave secreta de tipo SecretKeySpec apartir de la llave secreta.
	 * 
	 * @return SecretKeySpec lla secreta
	 * @throws Exception
	 */
	private static SecretKeySpec getSecretKey() throws Exception {
		MessageDigest sha;
		byte[] key = null;
		try {
			key = UtilsAes.getBytes(KEY_CLV);

			sha = MessageDigest.getInstance(SHA1);
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		return new SecretKeySpec(key, AES);
	}

}
