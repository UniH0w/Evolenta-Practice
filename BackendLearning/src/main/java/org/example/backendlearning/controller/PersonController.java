package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Person;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final List<Person> persons = new ArrayList<>(Arrays.asList(
            new Person(1, "Ivan", "Ivanovich", "Ivanov",
                    LocalDate.of(1999, 2, 3)),
            new Person(2, "Petr", "Petrovich", "Petrov",
                    LocalDate.of(2002, 2, 2))
    ));

    @GetMapping
    public List<Person> getPersons() {
        return persons;
    }

    @GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        persons.add(person);
        return person;
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable int id,
                               @RequestBody Person person) {

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() == id) {
                persons.set(i, person);
                return person;
            }
        }

        persons.add(person);
        return person;
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        persons.removeIf(p -> p.getId() == id);
    }
}