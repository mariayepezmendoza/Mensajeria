/**
 * 
 */
package com.maria.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Terminal class to create atributes and methods.
 * 
 * @author Maria
 */
@Table(name = "terminal")
@Entity
@Getter
@Setter
@ToString
public class Terminal {

	/**
     * Atributo long para identificar el terminal.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * Atributo cadena para codigo del terminal.
     */
	@Column(name = "codigo")
	private String codigo;

	/**
     * Atributo long para nombre del terminal.
     */
	@Column(name = "descripcion")
	private String descripcion;
}
