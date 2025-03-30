package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.repositories.MessageRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/messages") // Cambiado a /api/v1/messages para mejor semántica
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // get all messages
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // create message rest api
    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    // get message by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id :" + id));
        return ResponseEntity.ok(message);
    }

    // update message rest api
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message messageDetails) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id :" + id));

        message.setSubject(messageDetails.getSubject());
        message.setSender(messageDetails.getSender());
        // Asumiendo que quieres actualizar los campos de Communication también
        message.setReceiver(messageDetails.getReceiver());
        message.setSentDate(messageDetails.getSentDate());
        message.setContent(messageDetails.getContent());
        message.setRead(messageDetails.isRead());

        Message updatedMessage = messageRepository.save(message);
        return ResponseEntity.ok(updatedMessage);
    }

    // delete message rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMessage(@PathVariable Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id :" + id));

        messageRepository.delete(message);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}