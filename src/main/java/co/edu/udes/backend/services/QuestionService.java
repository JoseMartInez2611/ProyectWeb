package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.mappers.QuestionMapper;
import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.repositories.QualificationRepository;
import co.edu.udes.backend.repositories.QuestionRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionDTO> getAll() {
        return questionRepository.findAll().stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getById(Long id) {
        Question entity = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return questionMapper.toDTO(entity);
    }

    public QuestionDTO create(QuestionDTO dto) {
        Question entity = questionMapper.toEntity(dto);
        return questionMapper.toDTO(questionRepository.save(entity));
    }

    public QuestionDTO update(Long id, QuestionDTO dto) {
        questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Question updated = questionRepository.save(questionMapper.toEntity(dto));
        return questionMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Question entity = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        questionRepository.delete(entity);
    }
}
