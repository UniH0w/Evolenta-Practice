package org.example.backendlearning.service;

import org.example.backendlearning.dto.Person;
import org.example.backendlearning.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> getAll() {
        return repository.findAll();
    }

    public Optional<Person> getById(int id) {
        return repository.findById(id);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    @Transactional
    public ResponseEntity<Person> update(int id, Person person) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        person.setId(id);
        return ResponseEntity.ok(repository.save(person));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
