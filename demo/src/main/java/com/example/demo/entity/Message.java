package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public Message(final String sender, final String content) {
        this.sender = sender;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;


    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}