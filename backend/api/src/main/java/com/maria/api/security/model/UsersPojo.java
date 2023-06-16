package com.maria.api.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO class to store the users or client information in the database.
 * 
 * @author Maria
 */
@Getter
@Setter
public class UsersPojo {

	/**
     * Atributo cadena que almacena el usuario.
     */
	private String username;
	
	/**
     * Atributo cadena que almacena la clave.
     */
	private String password;
	
	/**
     * Atributo collection que almacena la lista de autorizados.
     */
	private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
}
