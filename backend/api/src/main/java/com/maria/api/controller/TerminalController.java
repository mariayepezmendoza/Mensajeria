/**
 * 
 */
package com.maria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maria.api.impl.service.TerminalService;
import com.maria.api.model.Terminal;

import io.reactivex.Observable;

/**
 * TerminalController class to create a API or HTTP Endpoints as a Resources for terminal, 
 * so that once the client pass throgh authentication then they will able to access the API.
 * 
 * @author Maria
 */
@RestController
public class TerminalController {

	/**
     * Atributo TerminalService para instanciar la clase.
     */
	@Autowired
	private TerminalService terminalService;

	/**
     * Method for obtain a list with all terminals.
     *
     * @return ResponseEntity
     */
	@GetMapping("/terminals")
	public ResponseEntity<List<Terminal>> getAllTerminal() {
		return ResponseEntity.ok().body(terminalService.getAllTerminal());
	}

	/**
     * Method for obtain a terminal for id.
     *
     * @param id valor identificador
     * @return ResponseEntity
     */
	@GetMapping("/terminals/{id}")
	public ResponseEntity<Observable<Terminal>> getAllTerminalById(@PathVariable long id) {
		return ResponseEntity.ok().body(terminalService.getTerminalById(id));
	}

	/**
     * Method for create terminals.
     *
     * @param terminals lista de terminales a registrar
     * @return ResponseEntity
     */
	@PostMapping("/terminals")
	public ResponseEntity<List<Terminal>> createTerminal(@RequestBody List<Terminal> terminals) {
		return ResponseEntity.ok().body(this.terminalService.createTerminal(terminals));
	}

	/**
     * Method for modify terminal for id
     *
     * @param id valor identificador del mensaje
     * @param terminal objeto con atributos de terminal
     * @return ResponseEntity
     */
	@PatchMapping("/terminals/{id}")
	public ResponseEntity<Observable<Terminal>> updateTerminal(@PathVariable long id, @RequestBody Terminal terminal) {
		terminal.setId(id);
		return ResponseEntity.ok().body(this.terminalService.updateTerminal(terminal));
	}

	/**
     * Method for delete a terminal.
     *
     * @param id valor identificador
     * @return HttpStatus
     */
	@DeleteMapping("/terminals/{id}")
	public HttpStatus deleteTerminal(@PathVariable long id) {
		this.terminalService.deleteTerminal(id);
		return HttpStatus.OK;
	}


	/**
     * Method for obtain a terminal for code.
     *
     * @param code valor codigo
     * @return ResponseEntity
     */
	@GetMapping("/terminal/{code}")
	public ResponseEntity<Observable<Terminal>> getAllTerminalByCode(@PathVariable String code) {
		return ResponseEntity.ok().body(terminalService.getTerminalByCode(code));
	}
}
