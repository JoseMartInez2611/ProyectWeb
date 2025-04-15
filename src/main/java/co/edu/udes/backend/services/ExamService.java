package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.mappers.ExamMapper;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.repositories.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    @Autowired
    private ExamMapper examMapper;

    public List<ExamDTO> getAll() {
        List<Exam> exams = examRepository.findAll();
        return examMapper.toDtoList(exams);
    }

    public ExamDTO getById(Long id) {
        return examMapper.toDto(examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id)));
    }

    public ExamDTO create(Exam exam) {
        return examMapper.toDto(examRepository.save(exam));

    }

    public List<ExamDTO> createMultiple(List<Exam> users) {
        return examMapper.toDtoList(
                examRepository.saveAll(users)
        );
    }

    public ExamDTO update(Long id, Exam exam) {
        examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        exam.setId(id);
        return examMapper.toDto(examRepository.save(exam));
    }

    public void delete(Long id) {
        examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        examRepository.deleteById(id);
    }
}
