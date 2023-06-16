/**
 * 
 */
package com.maria.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.maria.api.security.service.UsersService;

/**
 * SecurityConfiguration class that helps to enable Web Security.
 * 
 * @author Maria
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
     * Atributo UsersService para instanciar clase.
     */
	@Autowired
	private UsersService usersService;

	/**
     * Method return the object or bean of BCryptPasswordEncoder.
     *
     * @return PasswordEncoder
     */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
     * Method for configure AuthenticationManagerBuilder.
     *
     * @param auth  value for further adding types of authentication
     * 
     */
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(encoder());
	}

	/**
     * Method for configure HttpSecurity.
     *
     * @param http value for further adding policy.
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}

	/**
     * Method for configure WebSecurity.
     *
     * @param web for ignoring
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring();
	}

	/**
     * Method for converts to the hash format at the time of user authentication.
     * 
     * @return AuthenticationManager
     *  
     */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
