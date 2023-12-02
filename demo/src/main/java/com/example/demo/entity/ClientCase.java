package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// ClientCase.java
@Entity
public class ClientCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientReference;

    @OneToMany(mappedBy = "clientCase", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public ClientCase(final Long id, final String clientName, final String clientReference,
            final List<Message> messages) {
        this.id = id;
        this.clientName = clientName;
        this.clientReference = clientReference;
        this.messages = messages;
    }
    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientReference() {
        return clientReference;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setClientName(final String clientName) {
        this.clientName = clientName;
    }

    public void setClientReference(final String clientReference) {
        this.clientReference = clientReference;
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

}