/**
 * 
 */
package com.maria.api.impl.service;

import java.util.List;

import com.maria.api.model.Message;

import io.reactivex.Observable;

/**
 * MessageService interface class is to declare methods to implement.
 * 
 * @author Maria
 */
public interface MessageService {

	/**
     * Method interface for send messages.
     *
     * @param message lista de terminales a registrar
     * @return Observable (String)
     */
	Observable<String> registerMessage(List<Message> message);

	/**
     * Method interface for modify message for id
     *
     * @param message objeto con atributos de mensaje
     * @return Observable (String)
     */
	Observable<String> updateMessage(Message message);

	/**
     * Method interface for obtain a list with all messages.
     *
     * @return List (Message)
     */
	List<Message> getAllMessage();

	/**
     * Method interface for obtain a message for id.
     *
     * @param messageId valor identificador
     * @return Observable (Message)
     */
	Observable<Message> getMessageById(long messageId);

	/**
     * Method interface for delete a message.
     *
     * @param messageId valor identificador
     * 
     * @return Observable (String)
     */
	Observable<String> deleteMessage(long messageId);
}
