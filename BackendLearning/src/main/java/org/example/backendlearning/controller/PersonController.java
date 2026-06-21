package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Message;
import org.example.backendlearning.dto.Person;
import org.example.backendlearning.service.MessageService;
import org.example.backendlearning.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final MessageService messageService;

    public PersonController(PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    @GetMapping
    public Iterable<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        return personService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }

    @GetMapping("/{p_id}/message")
    public List<Message> getMessages(@PathVariable("p_id") int personId) {
        return messageService.getAllByPerson(personId);
    }

    @GetMapping("/{p_id}/message/{m_id}")
    public ResponseEntity<Message> getMessage(@PathVariable("p_id") int personId,
                                              @PathVariable("m_id") int messageId) {
        return messageService.getByPersonAndId(personId, messageId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{p_id}/message")
    public ResponseEntity<Message> createMessage(@PathVariable("p_id") int personId,
                                                 @RequestBody Message message) {
        return messageService.createForPerson(personId, message);
    }

    @DeleteMapping("/{p_id}/message/{m_id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("p_id") int personId,
                                              @PathVariable("m_id") int messageId) {
        return messageService.deleteForPerson(personId, messageId);
    }
}
