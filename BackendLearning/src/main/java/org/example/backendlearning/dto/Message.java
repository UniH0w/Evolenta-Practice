package org.example.backendlearning.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String text;
    private LocalDateTime time;

    public Message() {
    }

    public Message(String title, String text, LocalDateTime time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }

}