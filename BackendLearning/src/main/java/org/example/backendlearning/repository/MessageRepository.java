package org.example.backendlearning.repository;

import org.example.backendlearning.dto.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByPerson_Id(int personId);

}
