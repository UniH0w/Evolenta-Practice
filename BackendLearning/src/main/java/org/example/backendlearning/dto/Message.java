package org.example.backendlearning.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private int id;
    private String title;
    private String text;
    private LocalDateTime time;

    public Message(){

    }

    public Message(int id, String title, String text, LocalDateTime time){
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
    }
}
