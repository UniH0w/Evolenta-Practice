package org.example.backendlearning.controller;

import org.example.backendlearning.dto.Message;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(
                    1,
                    "Первое сообщение",
                    "Текст первого сообщения",
                    LocalDateTime.now()
            ),
            new Message(
                    2,
                    "Второе сообщение",
                    "Текст второго сообщения",
                    LocalDateTime.now()
            )
    ));

    @GetMapping
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable int id) {
        return messages.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id,
                                 @RequestBody Message message) {

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() == id) {
                messages.set(i, message);
                return message;
            }
        }

        messages.add(message);
        return message;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        messages.removeIf(m -> m.getId() == id);
    }
}