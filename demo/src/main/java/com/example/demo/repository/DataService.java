package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.ClientCase;
import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;

// DataService.java
@Service
public class DataService {
    private List<Message> messages = new ArrayList<>();
    private List<ClientCase> clientCases = new ArrayList<>();

    public Message saveMessage(Message message) {
        message.setId(generateId());
        messages.add(message);
        return message;
    }

    public ClientCase saveClientCase(ClientCase clientCase) {
        clientCase.setId(generateId());
        clientCases.add(clientCase);
        return clientCase;
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public List<ClientCase> getAllClientCases() {
        return clientCases;
    }

    private Long generateId() {
        // Implement a simple ID generation logic based on your requirements
        return System.currentTimeMillis();
    }

    public ClientCase getClientCasesByName(String clientName) {
        return clientCases.stream()
                .filter(clientCase -> clientCase.getClientName().equalsIgnoreCase(clientName))
                .collect(Collectors.toList()).get(0);
    }
    public ClientCase addClientReference(String clientName, String reference) {
        // Find the client case by Name
        ClientCase clientCase = getClientCasesByName(clientName);

        if (clientCase != null) {
            // Update the client reference
            clientCase.setClientReference(reference);

            return clientCase;
        } else {
            // Handle case not found
            return null;
        }
    }

    public void deleteMessageByName(String sender) {
        messages.removeIf(message -> message.getSender().equalsIgnoreCase(sender));
    }

    public void deleteClientCaseByReference(String reference) {
        // Using Iterator to safely remove from the list during iteration
        Iterator<ClientCase> iterator = clientCases.iterator();
        while (iterator.hasNext()) {
            ClientCase clientCase = iterator.next();
            if (clientCase.getClientReference()!=null && clientCase.getClientReference().equalsIgnoreCase(reference)) {
                iterator.remove();
            }
        }
    }
}