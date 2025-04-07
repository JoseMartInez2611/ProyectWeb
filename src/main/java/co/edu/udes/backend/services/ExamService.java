package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.mappers.ExamMapper;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.repositories.ExamRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public List<ExamDTO> getAll() {
        return examRepository.findAll().stream()
                .map(examMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ExamDTO getById(Long id) {
        Exam entity = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return examMapper.toDTO(entity);
    }

    public ExamDTO create(ExamDTO dto) {
        Exam entity = examMapper.toEntity(dto);
        return examMapper.toDTO(examRepository.save(entity));
    }

    public ExamDTO update(Long id, ExamDTO dto) {
        examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Exam updated = examRepository.save(examMapper.toEntity(dto));
        return examMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Exam entity = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        examRepository.delete(entity);
    }
}
