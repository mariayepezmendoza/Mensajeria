/**
 * 
 */
package com.maria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maria.api.model.Message;

/**
 * IMessageRepository interface class for obtain message's details
 * from the database.
 * 
 * @author Maria
 */
@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {

}