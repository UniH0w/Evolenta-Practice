package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Message;
import org.example.backendlearning.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MessageController {

    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> getMessage(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        return repository.save(message);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable int id,
            @RequestBody Message message) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setTitle(message.getTitle());
                    existing.setText(message.getText());
                    existing.setTime(message.getTime());

                    return new ResponseEntity<>(
                            repository.save(existing),
                            HttpStatus.OK
                    );
                })
                .orElseGet(() -> {
                    message.setId(id);
                    return new ResponseEntity<>(
                            repository.save(message),
                            HttpStatus.CREATED
                    );
                });
    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }
}