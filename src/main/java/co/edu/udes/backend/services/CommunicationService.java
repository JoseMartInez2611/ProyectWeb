package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.mappers.CommunicationMapper;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.repositories.CommunicationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationRepository communicationRepository;

    public List<CommunicationDTO> getAll() {
        List<Communication> communications = communicationRepository.findAll();
        return CommunicationMapper.INSTANCE.toDtoList(communications);
    }

    public CommunicationDTO getById(Long id) {
        return CommunicationMapper.INSTANCE.toDto(communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not found with id: " + id)));
    }

    public CommunicationDTO create(Communication communication) {
        return CommunicationMapper.INSTANCE.toDto(communicationRepository.save(communication));

    }

    public CommunicationDTO update(Long id, Communication communication) {
        communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not found with id: " + id));
        communication.setId(id);
        return CommunicationMapper.INSTANCE.toDto(communicationRepository.save(communication));
    }

    public void delete(Long id) {
        communicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Communication not found with id: " + id));
        communicationRepository.deleteById(id);
    }
}
