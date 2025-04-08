package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.mappers.QuestionMapper;
import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionDTO> getAll() {
        List<Question> questions =   questionRepository.findAll();
        return QuestionMapper.INSTANCE.toDtoList(questions);
    }

    public QuestionDTO getById(Long id) {
        return QuestionMapper.INSTANCE.toDto(questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id)));
    }

    public QuestionDTO create(Question question) {
        return QuestionMapper.INSTANCE.toDto(questionRepository.save(question));

    }

    public QuestionDTO update(Long id, Question question) {
        questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        question.setId(id);
        return QuestionMapper.INSTANCE.toDto(questionRepository.save(question));
    }

    public void delete(Long id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        questionRepository.deleteById(id);
    }
}
