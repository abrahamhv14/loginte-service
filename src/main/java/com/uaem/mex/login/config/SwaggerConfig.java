package com.uaem.mex.login.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author abrahamhv
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Bean de configuracion swagger.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("login usuarios").select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo());
	}

	/**
	 * Método de que proporciona información del microservicio.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {

		return new ApiInfo("Login Usuarios", "Autenticacion de usuarios", "0.0.1-SNAPSHOT", "Terminos de servicio",
				new Contact("AbrahamHV", "", "abraham.hdz.v14@gmail.com"), "FREE", "", new ArrayList<>());
	}

}