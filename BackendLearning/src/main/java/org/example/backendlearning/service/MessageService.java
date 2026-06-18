package org.example.backendlearning.service;

import org.example.backendlearning.dto.Message;
import org.example.backendlearning.dto.Person;
import org.example.backendlearning.repository.MessageRepository;
import org.example.backendlearning.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;

    public MessageService(MessageRepository messageRepository,
                          PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    public Message createStandaloneMessage(Message message) {
        message.setId(0);
        message.setTime(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Transactional
    public ResponseEntity<Message> updateMessage(int id, Message updated) {
        Optional<Message> existingOpt = messageRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Message existing = existingOpt.get();
        LocalDateTime time = existing.getTime();
        Person person = existing.getPerson();

        messageRepository.deleteById(id);
        updated.setId(id);
        updated.setTime(time);
        updated.setPerson(person);

        return ResponseEntity.ok(messageRepository.save(updated));
    }

    public ResponseEntity<Void> deleteMessage(int id) {
        if (!messageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        messageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List<Message> getAllByPerson(int personId) {
        return messageRepository.findByPerson_Id(personId);
    }

    public Optional<Message> getByPersonAndId(int personId, int messageId) {
        return messageRepository.findById(messageId)
                .filter(m -> m.getPerson() != null && m.getPerson().getId() == personId);
    }

    public ResponseEntity<Message> createForPerson(int personId, Message message) {
        Optional<Person> personOpt = personRepository.findById(personId);

        if (personOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Person person = personOpt.get();

        message.setId(0);
        message.setTime(LocalDateTime.now());
        message.setPerson(person);

        return ResponseEntity.ok(messageRepository.save(message));
    }

    public ResponseEntity<Void> deleteForPerson(int personId, int messageId) {
        Optional<Message> messageOpt = messageRepository.findById(messageId);

        if (messageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Message message = messageOpt.get();

        if (message.getPerson() == null || message.getPerson().getId() != personId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
