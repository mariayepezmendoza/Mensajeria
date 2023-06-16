package com.maria.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maria.api.security.model.UsersHelper;
import com.maria.api.security.model.UsersPojo;
import com.maria.api.security.repository.UsersDBQuery;

/**
 * UsersService class to call the DAO class or Repository class as shown below.
 * 
 * @author Maria
 */
@Service
public class UsersService implements UserDetailsService {

	/**
     * Atributo UsersDBQuery para instanciar clase.
     */
	@Autowired
	UsersDBQuery usersDBQuery;

	/**
     * Method for search and load user from database.
     *
     * @param username valor condicional para buscar en database 
     */
	@Override
	public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
		UsersPojo usersPojo = null;
		try {
			usersPojo = usersDBQuery.getUserDetails(username);
			UsersHelper userDetail = new UsersHelper(usersPojo);
			return userDetail;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

}
