package com.uaem.mex.login.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Utileria con metodos estaticos para su uso en la capa de negocio.
 * 
 * @author abrahamhv
 *
 */
public final class Utils {

	private static final Logger log = LoggerFactory.getLogger(Utils.class);

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

}
