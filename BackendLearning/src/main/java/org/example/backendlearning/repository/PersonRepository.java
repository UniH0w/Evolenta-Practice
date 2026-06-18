package org.example.backendlearning.repository;

import org.example.backendlearning.dto.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}