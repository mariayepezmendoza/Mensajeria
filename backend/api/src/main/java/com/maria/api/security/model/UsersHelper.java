package com.maria.api.security.model;

import org.springframework.security.core.userdetails.User;

/**
 * UsersHelper class is to authenticate the user.
 * 
 * @author Maria
 */
public class UsersHelper extends User{

	/**
     * Method for create authenticate the user.
     *
     * @param user atributos del usuario.
     */
	private static final long serialVersionUID = 1L;
	   public UsersHelper(UsersPojo user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }
}
