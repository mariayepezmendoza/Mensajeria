/**
 * 
 */
package com.maria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maria.api.impl.service.MessageService;
import com.maria.api.model.Message;

import io.reactivex.Observable;

/**
 * MessageController class to create a API or HTTP Endpoints as a Resources for message, 
 * so that once the client pass throgh authentication then they will able to access the API.
 * 
 * @author Maria
 */
@RestController
public class MessageController {

	/**
     * Atributo MessageService para instanciar la clase.
     */
	@Autowired
	private MessageService messageService;

	/**
     * Method for obtain a list with all messages.
     *
     * @return ResponseEntity
     */
	@GetMapping("/receive")
	public ResponseEntity<List<Message>> getAllMessage() {
		return ResponseEntity.ok().body(messageService.getAllMessage());
	}

	/**
     * Method for obtain a message for id.
     *
     * @param id valor identificador
     * @return ResponseEntity
     */
	@GetMapping("/receive/{id}")
	public ResponseEntity<Observable<Message>> getAllMessageById(@PathVariable long id) {
		return ResponseEntity.ok().body(messageService.getMessageById(id));
	}

	/**
     * Method for send message.
     *
     * @param messages lista de mensajes a enviar
     * @return message
     */
	@PostMapping("/send")
	public Observable<String> registerMessage(@RequestBody List<Message> messages) {
		Observable<String> message = this.messageService.registerMessage(messages);
		return message;
	}

	/**
     * Method for modify message for id
     *
     * @param id valor identificador del mensaje
     * @param message objeto con atributos de mensaje
     * @return mensaje
     */
	@PutMapping("/send/{id}")
	public Observable<String> updateMessage(@PathVariable long id, @RequestBody Message message) {
		message.setId(id);
		Observable<String> mensaje = this.messageService.updateMessage(message);
		return mensaje;
	}

	/**
     * Method for delete a message.
     *
     * @param id valor identificador
     * @return message
     */
	@DeleteMapping("/messages/{id}")
	public Observable<String> deleteMessage(@PathVariable long id) {
		Observable<String> message = this.messageService.deleteMessage(id);
		return message;
	}
}
