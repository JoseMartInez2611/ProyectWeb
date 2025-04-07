package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.mappers.AnswerDocumentMapper;
import co.edu.udes.backend.models.AnswerDocument;
import co.edu.udes.backend.repositories.AnswerDocumentRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerDocumentService {
    private final AnswerDocumentRepository answerDocumentRepository;
    private final AnswerDocumentMapper answerDocumentMapper;

    public List<AnswerDocumentDTO> getAll() {
        return answerDocumentRepository.findAll().stream()
                .map(answerDocumentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AnswerDocumentDTO getById(Long id) {
        AnswerDocument entity = answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return answerDocumentMapper.toDTO(entity);
    }

    public AnswerDocumentDTO create(AnswerDocumentDTO dto) {
        AnswerDocument entity = answerDocumentMapper.toEntity(dto);
        return answerDocumentMapper.toDTO(answerDocumentRepository.save(entity));
    }

    public AnswerDocumentDTO update(Long id, AnswerDocumentDTO dto) {
        answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        AnswerDocument updated = answerDocumentRepository.save(answerDocumentMapper.toEntity(dto));
        return answerDocumentMapper.toDTO(updated);
    }

    public void delete(Long id) {
        AnswerDocument entity = answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        answerDocumentRepository.delete(entity);
    }
}
