package com.example.demo.repository.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.ClientCase;
import com.example.demo.entity.Message;
import com.example.demo.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// ApiController.java
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DataService dataService;

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return dataService.saveMessage(message);
    }

    @PostMapping("/client-cases")
    public ClientCase createClientCase(@RequestBody ClientCase clientCase) {
        return dataService.saveClientCase(clientCase);
    }

    @PostMapping("/client-cases-with-existing-message")
    public ClientCase createClientCaseWithPreviouslyCreatedMessage(@RequestBody ClientCase clientCase) {
        clientCase.setMessages(new ArrayList<>());
        clientCase.getMessages().add(dataService.getAllMessages().get(0));
        return dataService.saveClientCase(clientCase);
    }

    @PostMapping("/create-message-and-link-to-previous-clientcase")
    public ClientCase createMessageAndLinkItToPreviousClientCase(@RequestBody Message message) {
        dataService.saveMessage(message);
        dataService.getAllClientCases().get(0).getMessages().add(message);
        return dataService.getAllClientCases().get(0);
    }

    @PutMapping("/client-cases/add-reference")
    public ClientCase addClientReference(
            @RequestParam String clientName,
            @RequestParam String reference
    ) {
        return dataService.addClientReference(clientName, reference);
    }

    @GetMapping("/client-cases")
    public List<ClientCase> getAllClientCases() {
        return dataService.getAllClientCases();
    }

    @DeleteMapping("/messages/delete")
    public List<Message> deleteMessageByName(@RequestParam String sender) {
        dataService.deleteMessageByName(sender);
        return dataService.getAllMessages();
    }

    @DeleteMapping("/client-cases/delete")
    public List<ClientCase> deleteClientCaseByReference(@RequestParam String reference) {
        dataService.deleteClientCaseByReference(reference);
        return dataService.getAllClientCases();
    }
}
