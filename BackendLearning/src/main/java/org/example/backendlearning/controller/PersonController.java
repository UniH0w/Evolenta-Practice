package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Person;
import org.example.backendlearning.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> getPerson(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable int id,
            @RequestBody Person person) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setFirstname(person.getFirstname());
                    existing.setSurname(person.getSurname());
                    existing.setLastname(person.getLastname());
                    existing.setBirthday(person.getBirthday());

                    return new ResponseEntity<>(
                            repository.save(existing),
                            HttpStatus.OK
                    );
                })
                .orElseGet(() -> {
                    person.setId(id);
                    return new ResponseEntity<>(
                            repository.save(person),
                            HttpStatus.CREATED
                    );
                });
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}