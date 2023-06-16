/**
 * 
 */
package com.maria.api.security.controller;

/**
 * HelloWorldController class to create a API or HTTP Endpoints as a Resources, so that 
 * once the client pass throgh authentication then they will able to access the API.
 * 
 * @author Maria
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	/**
     * Method for create a resource.
     *
     * @return String message
     */
	@RequestMapping("/home")
	public String index() {
		return "Bienvenido a la mensajería!";
	}

}
