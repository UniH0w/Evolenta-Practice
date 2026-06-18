package org.example.backendlearning.service;

import org.example.backendlearning.dto.Person;
import org.example.backendlearning.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Person> update(int id, Person person) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setFirstname(person.getFirstname());
                    existing.setSurname(person.getSurname());
                    existing.setLastname(person.getLastname());
                    existing.setBirthday(person.getBirthday());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
