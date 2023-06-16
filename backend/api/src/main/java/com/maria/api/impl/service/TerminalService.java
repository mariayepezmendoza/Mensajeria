/**
 * 
 */
package com.maria.api.impl.service;

import java.util.List;

import com.maria.api.model.Terminal;

import io.reactivex.Observable;

/**
 * TerminalService interface class is to declare methods to implement.
 * 
 * @author Maria
 */
public interface TerminalService {

	/**
     * Method interface for create terminals.
     *
     * @param terminals lista de terminales a registrar
     * @return List<Terminal>
     */
	List<Terminal> createTerminal(List<Terminal> Terminal);

	/**
     * Method interface for modify terminal for id
     *
     * @param terminal objeto con atributos de terminal
     * @return Observable<Terminal>
     */
	Observable<Terminal> updateTerminal(Terminal Terminal);

	/**
     * Method interface for obtain a list with all terminals.
     *
     * @return List<Terminal>
     */
	List<Terminal> getAllTerminal();

	/**
     * Method interface for obtain a terminal for id.
     *
     * @param terminalId valor identificador
     * @return Observable<Terminal>
     */
	Observable<Terminal> getTerminalById(long TerminalId);

	/**
     * Method interface for delete a terminal.
     *
     * @param terminalId valor identificador
     */
	void deleteTerminal(long TerminalId);

	/**
     * Method interface for obtain a terminal for code.
     *
     * @param codigo valor codigo
     * @return Observable<Terminal>
     */
	Observable<Terminal> getTerminalByCode(String code);
}
