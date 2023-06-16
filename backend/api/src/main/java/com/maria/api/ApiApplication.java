package com.maria.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Enable the Authorization Server and Resource Server.
 * 
 * @author Maria
 */
@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class ApiApplication {

	/**
     * Método main
     *
     * @param args argumentos para iniciar la aplicación.
     */
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
