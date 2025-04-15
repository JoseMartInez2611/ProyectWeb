package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QuestionDTO;
import co.edu.udes.backend.mappers.QuestionMapper;
import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> getAll() {
        List<Question> questions =   questionRepository.findAll();
        return questionMapper.toDtoList(questions);
    }

    public QuestionDTO getById(Long id) {
        return questionMapper.toDto(questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id)));
    }

    public QuestionDTO create(Question question) {
        return questionMapper.toDto(questionRepository.save(question));

    }

    public List<QuestionDTO> createMultiple(List<Question> users) {
        return questionMapper.toDtoList(
                questionRepository.saveAll(users)
        );
    }

    public QuestionDTO update(Long id, Question question) {
        questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        question.setId(id);
        return questionMapper.toDto(questionRepository.save(question));
    }

    public void delete(Long id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        questionRepository.deleteById(id);
    }
}
