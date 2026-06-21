package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Message;
import org.example.backendlearning.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return service.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) {
        return service.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return service.createStandaloneMessage(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id,
                                                 @RequestBody Message message) {
        return service.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        return service.deleteMessage(id);
    }
}
