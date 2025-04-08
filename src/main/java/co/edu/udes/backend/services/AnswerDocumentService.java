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

    public List<AnswerDocumentDTO> getAll() {
        List<AnswerDocument> answerDocuments = answerDocumentRepository.findAll();
        return AnswerDocumentMapper.INSTANCE.toDtoList(answerDocuments);
    }

    public AnswerDocumentDTO getById(Long id) {
        return AnswerDocumentMapper.INSTANCE.toDto(answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer document not found with id: " + id)));
    }

    public AnswerDocumentDTO create(AnswerDocument answerDocument) {
        return AnswerDocumentMapper.INSTANCE.toDto(answerDocumentRepository.save(answerDocument));
    }

    public AnswerDocumentDTO update(Long id, AnswerDocument answerDocument) {
        answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer document not found with id: " + id));
        answerDocument.setId(id);
        return AnswerDocumentMapper.INSTANCE.toDto(answerDocumentRepository.save(answerDocument));
    }

    public void delete(Long id) {
        answerDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer document not found with id: " + id));
        answerDocumentRepository.deleteById(id);
    }
}
