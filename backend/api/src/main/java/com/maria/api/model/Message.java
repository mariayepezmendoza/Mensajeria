/**
 * 
 */
package com.maria.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Message class to create atributes and methods.
 * 
 * @author Maria
 */
@Table(name = "message")
@Entity
@Getter
@Setter
public class Message {

	/**
     * Atributo long para identificar la transaccion.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * Atributo cadena para almacenar/mostrar mensaje.
     */
	@Column(name = "contenido")
	private String contenido;

	/**
     * Atributo Terminal para identificar terminal
     */
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Terminal terminal;

	/**
     * Atributo cadena para la fecha y hora de la transaccion.
     */
	@Column(name = "fecha_hora")
	private String fechaHora;

	/**
     * Atributo cadena para numero de tarjeta.
     */
	@Column(name = "numero_tarjeta")
	private String numeroTarjeta;

	/**
     * Method for show message in plain text.
     *
     * @return string
     */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(contenido);
		sb.append(terminal.getCodigo());
		sb.append(fechaHora);
		sb.append(numeroTarjeta);
		return sb.toString();
	}

}
