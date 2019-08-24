package com.uaem.mex.login.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Clase de utlieria para encriptacion y desencriptacion usando el algoritmo AES
 * 128, se encriptan objetos de tipo String, haciendo uso de llave pedida en
 * parametros.
 * 
 * @author abrahamhv
 *
 */
@Slf4j
public final class UtilsAes {

	/**
	 * Codificación de las cadenas de texto
	 */
	private static final String UTF8 = "UTF-8";

	/**
	 * Nombre del esquema de cifrado que será utilizado
	 */
	private static final String AES = "AES";

	/**
	 * Nombre de la transformación que require el objeto Cipher
	 */
	private static final String AES_CIPHER = "AES/CBC/PKCS5Padding";

	/**
	 * Constructor privado.
	 */
	private UtilsAes() {

	}

	/**
	 * Genera de forma dinamica una llave secreta en formato Hexadecimal con el
	 * algoritmo AES 128
	 *
	 * @return cadena de texto de la llave
	 * @throws EncryptException en caso que falle la generacion de llave dinamica.
	 */
	public static String getRandomKey() {
		KeyGenerator generator = null;
		try {
			SecureRandom rand = new SecureRandom();
			generator = KeyGenerator.getInstance(AES);
			generator.init(128, rand);

		} catch (NoSuchAlgorithmException e) {
			log.error("error al generar llave");
		}
		return String.format("%032X", new BigInteger(+1, generator.generateKey().getEncoded()));
	}

	/**
	 * Genera una llave secreta de tipo SecretKeySpec apartir de la llave secreta.
	 *
	 * @param key llave secreta.
	 * @return llave sercreta de tipo SecretKeySpec.
	 */
	public static SecretKeySpec getSecretKey(String key) {
		return new SecretKeySpec(DatatypeConverter.parseHexBinary(key), AES);
	}

	/**
	 * Encripta una cadena a partir de su llave secreta.
	 *
	 * @param plainText     cadena a encriptar.
	 * @param secretKeySpec llave secreta.
	 * @return cadena base64 encriptada.
	 * @throws Exception
	 * @throws EncryptException en caso que la encriptacion falle.
	 */
	public static String encrypt(String plainText, SecretKeySpec secretKeySpec) throws Exception {
		String encrypt;
		Cipher cipher = getCipher();
		init(cipher, secretKeySpec, Cipher.ENCRYPT_MODE);
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			try {
				byte[] cipherText = cipher.doFinal(getBytes(plainText));
				byte[] iv = cipher.getIV();
				outputStream.write(iv);
				outputStream.write(cipherText);
				byte[] finalData = outputStream.toByteArray();
				encrypt = DatatypeConverter.printBase64Binary(finalData);
			} finally {
				outputStream.close();
			}
		} catch (IllegalBlockSizeException e) {
			throw new Exception(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new Exception(e.getMessage(), e);
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return encrypt;
	}

	/**
	 * Desencripta cadena a partir de su llave secreta.
	 *
	 * @param crypted       cadena que sera desencriptada.
	 * @param secretKeySpec llave secreta.
	 * @return cadena original.
	 * @throws EncryptException en caso que ocurra error al desencriptar.
	 */
	public static String decrypt(String crypted, SecretKeySpec secretKeySpec) throws Exception {
		String decrypt;
		Cipher cipher = getCipher();
		byte[] encryptedData = DatatypeConverter.parseBase64Binary(crypted);
		byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
		IvParameterSpec ivSpecs = new IvParameterSpec(iv);
		init(cipher, secretKeySpec, Cipher.DECRYPT_MODE, ivSpecs);
		try {
			byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
			byte[] plainTextBytes = cipher.doFinal(cipherText);
			decrypt = getStringOfBytes(plainTextBytes);
		} catch (IllegalBlockSizeException e) {
			throw new Exception(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new Exception(e.getMessage(), e);
		}
		return decrypt;
	}

	/**
	 * Obtiene String de arreglo de bytes.
	 *
	 * @param bytes arreglo de bytes.
	 * @return cadena obtenida.
	 * @throws UnsupportedEncodingException
	 * @throws EncryptException             en caso que falle la conversion de
	 *                                      bytes.
	 */
	private static String getStringOfBytes(byte[] bytes) throws UnsupportedEncodingException {
		String str;
		str = new String(bytes, UTF8);
		return str;
	}

	/**
	 * Crea una instacia de Cipher con el algoritmo AES.
	 *
	 * @return instacia de Cipher.
	 * @throws Exception
	 * @throws EncryptException en caso que no pudo crear la instacia de Cipher.
	 */
	private static Cipher getCipher() throws Exception {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(AES_CIPHER);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new Exception(e.getMessage(), e);
		}
		return cipher;
	}

	/**
	 * Inicializa cipher con la llave secreta y la opcion de encriptar o
	 * desencriptar.
	 *
	 * @param cipher        instacia cipher.
	 * @param secretKeySpec llave secreta.
	 * @param mode          opcion de encriptar o desencriptar.
	 * @throws Exception
	 * @throws EncryptException en caso que no se pueda inicalizar la instancia.
	 */
	private static void init(Cipher cipher, SecretKeySpec secretKeySpec, int mode) throws Exception {
		try {
			cipher.init(mode, secretKeySpec);
		} catch (InvalidKeyException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Inicializa cipher con la llave secreta y la opcion de encriptar o
	 * desencriptar.
	 *
	 * @param cipher        instacia cipher.
	 * @param secretKeySpec llave secreta.
	 * @param mode          opcion de encriptar o desencriptar.
	 * @param ivSpecs       cadena IV.
	 * @throws EncryptException en caso que no se pueda inicalizar la instancia.
	 */
	private static void init(Cipher cipher, SecretKeySpec secretKeySpec, int mode, IvParameterSpec ivSpecs)
			throws Exception {
		try {
			cipher.init(mode, secretKeySpec, ivSpecs);
		} catch (InvalidKeyException e) {
			throw new Exception(e.getMessage(), e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Obtiene los bytes de una cadena.
	 *
	 * @param str cadena de carateres.
	 * @return Arreglo de bytes.
	 * @throws EncryptException en caso que la cadena tenga un formato de
	 *                          codificacion invalido.
	 */
	public static byte[] getBytes(String str) throws Exception {
		byte[] key;
		try {
			key = str.getBytes(UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage(), e);
		}
		return key;
	}

}