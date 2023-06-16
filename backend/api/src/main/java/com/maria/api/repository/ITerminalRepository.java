/**
 * 
 */
package com.maria.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maria.api.model.Terminal;

/**
 * ITerminalRepository interface class for obtain terminal's details
 * from the database.
 * 
 * @author Maria
 */
@Repository
public interface ITerminalRepository extends JpaRepository<Terminal, Long> {

	@Query(value = "SELECT r FROM Terminal r WHERE r.codigo= :codigo")
	Optional<Terminal> getTerminalByCode(@Param("codigo") String codigo);
}
