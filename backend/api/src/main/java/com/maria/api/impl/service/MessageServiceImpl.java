/**
 * 
 */
package com.maria.api.impl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.api.exception.ResourceNotFoundException;
import com.maria.api.model.Message;
import com.maria.api.model.Terminal;
import com.maria.api.repository.IMessageRepository;
import com.maria.api.repository.ITerminalRepository;

import io.reactivex.Observable;

/**
 * MessageServiceImpl class to implement each HTTP Endpoints (Resources) for message.
 * 
 * @author Maria
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private static final Logger Log = LoggerFactory.getLogger(MessageServiceImpl.class);

	/**
     * Atributo IMessageRepository para instanciar la interface.
     */
	@Autowired
	private IMessageRepository messageRepository;

	/**
     * Atributo ITerminalRepository para instanciar la interface.
     */
	@Autowired
	private ITerminalRepository terminalRepository;

	/**
     * Method implement for send messages.
     *
     * @param messages lista de terminales a registrar
     * @return message
     */
	@Override
	public Observable<String> registerMessage(List<Message> messages) {
		String cadena = "";
		Observable<String> message = null;

		for (Message mensaje : messages) {
			Terminal terminal = verifyTerminal(mensaje);
			mensaje.setTerminal(terminal);
			messageRepository.save(mensaje);
			cadena = cadena + mensaje.toString();
		}
		message = Observable.just(cadena);
		message.subscribe(s -> Log.info(Constants.STR_MESSAGE + s.toString()));

		return message;
	}

	/**
     * Method implement for modify message for id
     *
     * @param message objeto con atributos de mensaje
     * @return mensaje
     */
	@Override
	public Observable<String> updateMessage(Message message) {
		Optional<Message> messageDb = this.messageRepository.findById(message.getId());
		
		String cadena = "";
		Observable<String> mensaje= null;
		
		if (messageDb.isPresent()) {
			Message messageForUpdate = messageDb.get();
			setValues(message, messageForUpdate);

			Observable<Message> source = Observable.just(messageForUpdate);
			cadena = cadena + messageForUpdate.toString();
			mensaje = Observable.just(cadena);
			source.subscribe(s -> Log.info(Constants.STR_MESSAGE + s.toString()));
			return mensaje;
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + message.getId());
		}
	}

	/**
     * Method implement for obtain a list with all messages.
     *
     * @return list
     */
	@Override
	public List<Message> getAllMessage() {
		List<Message> list = new ArrayList<Message>(messageRepository.findAll());
		Observable<Message> source = Observable.fromIterable(list);
		source.subscribe(s -> Log.info(Constants.STR_MESSAGE + s.toString()));
		return list;
	}

	/**
     * Method implement for obtain a message for id.
     *
     * @param messageId valor identificador
     * @return source
     */
	@Override
	public Observable<Message> getMessageById(long messageId) {
		Optional<Message> messageDb = this.messageRepository.findById(messageId);

		if (messageDb.isPresent()) {
			Observable<Message> source = Observable.just(messageDb.get());
			source.subscribe(s -> Log.info(Constants.STR_MESSAGE + s.toString()));
			return source;
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + messageId);
		}
	}

	/**
     * Method implement for delete a message.
     *
     * @param messageId valor identificador
     * 
     * @return message
     */
	@Override
	public Observable<String> deleteMessage(long messageId) {
		Optional<Message> messageDb = this.messageRepository.findById(messageId);
		Observable<String> message = null;
		try {
			if (messageDb.isPresent()) {
				this.messageRepository.delete(messageDb.get());
				message = Observable.just(Constants.STR_DELETE_OK);
				message.subscribe(s -> Log.info(Constants.STR_DELETE_OK));
			} else {
				message = Observable.just(Constants.STR_DELETE_ERROR);
				throw new ResourceNotFoundException(Constants.STR_ERROR_ID + messageId);
			}
		} catch (Exception e) {
			message = Observable.just(Constants.STR_DELETE_ERROR);
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + messageId);
		}

		return message;
	}

	/**
     * Method to verify terminal.
     *
     * @param mensaje valores actuales
     * 
     * @return Terminal
     * 
     */
	private Terminal verifyTerminal(Message mensaje) {
		Optional<Terminal> terminalDb = this.terminalRepository.getTerminalByCode(mensaje.getTerminal().getCodigo());

		Terminal terminal = new Terminal();
		if (terminalDb.isPresent()) {
			terminal.setId(terminalDb.get().getId());
			terminal.setCodigo(terminalDb.get().getCodigo());
			terminal.setDescripcion(terminalDb.get().getDescripcion());
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_CODE + mensaje.getTerminal().getCodigo());
		}

		return terminal;
	}

	/**
     * Method to values of message for update.
     *
     * @param message valores actuales
     * 
     * @param1 messageForUpdate valores a actualizar
     * 
     */
	private void setValues(Message message, Message messageForUpdate) {
		Terminal terminal = null;
		if (message.getId() != null) {
			messageForUpdate.setId(message.getId());
		}

		if (message.getContenido() != null) {
			messageForUpdate.setContenido(message.getContenido());
		}
		
		if (message.getTerminal() != null && !message.getTerminal().getCodigo().equals("")) {
			terminal = verifyTerminal(message);
			messageForUpdate.setTerminal(terminal);
		}

		if (message.getFechaHora() != null) {
			messageForUpdate.setFechaHora(message.getFechaHora());
		}

		if (message.getNumeroTarjeta() != null) {
			messageForUpdate.setNumeroTarjeta(message.getNumeroTarjeta());
		}
	}

}
