package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Message;
import org.example.backendlearning.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }


    @GetMapping("/message")
    public List<Message> getAllMessages() {
        return service.getAllMessages();
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) {
        return service.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message) {
        return service.createStandaloneMessage(message);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id,
                                                 @RequestBody Message message) {
        return service.updateMessage(id, message);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        return service.deleteMessage(id);
    }

    @GetMapping("/person/{personId}/message")
    public List<Message> getAllByPerson(@PathVariable int personId) {
        return service.getAllByPerson(personId);
    }

    @GetMapping("/person/{personId}/message/{messageId}")
    public ResponseEntity<Message> getByPersonAndId(@PathVariable int personId,
                                                    @PathVariable int messageId) {
        return service.getByPersonAndId(personId, messageId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/person/{personId}/message")
    public ResponseEntity<Message> createForPerson(@PathVariable int personId,
                                                   @RequestBody Message message) {
        return service.createForPerson(personId, message);
    }

    @DeleteMapping("/person/{personId}/message/{messageId}")
    public ResponseEntity<Void> deleteForPerson(@PathVariable int personId,
                                                @PathVariable int messageId) {
        return service.deleteForPerson(personId, messageId);
    }
}
