package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.repositories.CommunicationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/communications")
public class CommunicationController {
    @Autowired
    private CommunicationRepository communicationRepository;

    // get all communications
    @GetMapping
    public List<Communication> getAllCommunications() {
        return communicationRepository.findAll();
    }

    // create communication rest api
    @PostMapping
    public Communication createCommunication(@RequestBody Communication communication) {
        return communicationRepository.save(communication);
    }

    // get communication by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Communication> getCommunicationById(@PathVariable Long id) {
        Communication communication = communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not exist with id :" + id));
        return ResponseEntity.ok(communication);
    }

    // update communication rest api
    @PutMapping("/{id}")
    public ResponseEntity<Communication> updateCommunication(@PathVariable Long id, @RequestBody Communication communicationDetails) {
        Communication communication = communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not exist with id :" + id));

        communication.setReceiver(communicationDetails.getReceiver());
        communication.setSentDate(communicationDetails.getSentDate());
        communication.setContent(communicationDetails.getContent());
        communication.setRead(communicationDetails.isRead());

        Communication updatedCommunication = communicationRepository.save(communication);
        return ResponseEntity.ok(updatedCommunication);
    }

    // delete communication rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCommunication(@PathVariable Long id) {
        Communication communication = communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not exist with id :" + id));

        communicationRepository.delete(communication);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
