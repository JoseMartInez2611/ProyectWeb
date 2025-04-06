package co.edu.udes.backend.service;

import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.repositories.CommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunicationService {

    private final CommunicationRepository communicationRepository;

    @Autowired
    public CommunicationService(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    public List<Communication> getAllCommunications() {
        return communicationRepository.findAll();
    }

    public Optional<Communication> getCommunicationById(Long id) {
        return communicationRepository.findById(id);
    }

    public Communication saveCommunication(Communication communication) {
        return communicationRepository.save(communication);
    }

    public void deleteCommunication(Long id) {
        communicationRepository.deleteById(id);
    }

    // Add other business logic (e.g., mark as read, find by receiver)
}