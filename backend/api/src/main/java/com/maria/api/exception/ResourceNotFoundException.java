/**
 * 
 */
package com.maria.api.exception;

/**
 * ResourceNotFoundException class to manage not found exception.
 * 
 * @author Maria
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
     * Atributo long para serializar la clase.
     */
	private static final long serialVersionUID = 6110287434355011646L;

	/**
     * Constructor method for send exception's message.
     *
     * @param message almacena el error en una cadena
     */
	public ResourceNotFoundException(String message) {
		super(message);
	}

	/**
     * Constructor method for send exception's details.
     *
     * @param message almacena el error en una cadena
     * @param1 throwable almacena el error en una superclase
     */
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
