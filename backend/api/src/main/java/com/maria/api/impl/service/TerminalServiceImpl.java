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
import com.maria.api.model.Terminal;
import com.maria.api.repository.ITerminalRepository;

import io.reactivex.Observable;

/**
 * TerminalServiceImpl class to implement each HTTP Endpoints (Resources) for terminal.
 * 
 * @author Maria
 */
@Service
@Transactional
public class TerminalServiceImpl implements TerminalService {

	/**
     * Atributo logger para la clase.
     */
	private static final Logger Log = LoggerFactory.getLogger(TerminalServiceImpl.class);

	/**
     * Atributo ITerminalRepository para instanciar la interface.
     */
	@Autowired
	private ITerminalRepository terminalRepository;

	/**
     * Method implement for create terminals.
     *
     * @param terminals lista de terminales a registrar
     * @return List<Terminal>
     */
	@Override
	public List<Terminal> createTerminal(List<Terminal> terminals) {
		for (Terminal terminal : terminals) {
			Observable<Terminal> source = Observable.just(terminalRepository.save(terminal));
			source.subscribe(s -> Log.info(Constants.STR_TERMINAL + s.toString()));
		}
		return terminals;
	}

	/**
     * Method implement for modify terminal for id
     *
     * @param terminal objeto con atributos de terminal
     * @return Observable<Terminal>
     */
	@Override
	public Observable<Terminal> updateTerminal(Terminal terminal) {
		Optional<Terminal> terminalDb = this.terminalRepository.findById(terminal.getId());

		if (terminalDb.isPresent()) {
			Terminal terminalUpdate = terminalDb.get();
			setValues(terminal, terminalUpdate);

			Observable<Terminal> source = Observable.just(terminalUpdate);
			source.subscribe(s -> Log.info(Constants.STR_TERMINAL + s.toString()));
			return source;
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + terminal.getId());
		}
	}

	/**
     * Method implement for obtain a list with all terminals.
     *
     * @return List<Terminal>
     */
	@Override
	public List<Terminal> getAllTerminal() {
		List<Terminal> list = new ArrayList<Terminal>(terminalRepository.findAll());
		Observable<Terminal> source = Observable.fromIterable(list);
		source.subscribe(s -> Log.info(Constants.STR_TERMINAL + s.toString()));
		return list;
	}

	/**
     * Method implement for obtain a terminal for id.
     *
     * @param terminalId valor identificador
     * @return Observable<Terminal>
     */
	@Override
	public Observable<Terminal> getTerminalById(long terminalId) {
		Optional<Terminal> terminalDb = this.terminalRepository.findById(terminalId);

		if (terminalDb.isPresent()) {
			Observable<Terminal> source = Observable.just(terminalDb.get());
			source.subscribe(s -> Log.info(Constants.STR_TERMINAL + s.toString()));
			return source;
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + terminalId);
		}
	}

	/**
     * Method implement for delete a terminal.
     *
     * @param terminalId valor identificador
     */
	@Override
	public void deleteTerminal(long terminalId) {
		Optional<Terminal> terminalDb = this.terminalRepository.findById(terminalId);

		if (terminalDb.isPresent()) {
			this.terminalRepository.delete(terminalDb.get());
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + terminalId);
		}
	}

	/**
     * Method implement for obtain a terminal for code.
     *
     * @param codigo valor codigo
     * @return Observable<Terminal>
     */
	@Override
	public Observable<Terminal> getTerminalByCode(String codigo) {
		Optional<Terminal> terminalDb = this.terminalRepository.getTerminalByCode(codigo);

		if (terminalDb.isPresent()) {
			Observable<Terminal> source = Observable.just(terminalDb.get());
			source.subscribe(s -> Log.info(Constants.STR_TERMINAL + s.toString()));
			return source;
		} else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_CODE + codigo);
		}
	}

	/**
     * Method to values of terminal for update.
     *
     * @param terminal valores actuales
     * 
     * @param1 terminalUpdate valores a actualizar
     * 
     */
	private void setValues(Terminal terminal, Terminal terminalUpdate) {
		if (terminal.getId() != null) {
			terminalUpdate.setId(terminal.getId());
		}

		if (terminal.getCodigo() != null) {
			if (!terminal.getCodigo().trim().equals("")) {
				terminalUpdate.setCodigo(terminal.getCodigo());
			}
		}

		if (terminal.getDescripcion() != null) {
			if (!terminal.getDescripcion().trim().equals("")) {
				terminalUpdate.setDescripcion(terminal.getDescripcion());
			}
		}
	}
}
