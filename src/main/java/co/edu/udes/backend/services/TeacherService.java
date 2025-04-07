package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.repositories.TeacherRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherDTO> getAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getById(Long id) {
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return teacherMapper.toDTO(entity);
    }

    public TeacherDTO create(TeacherDTO dto) {
        Teacher entity = teacherMapper.toEntity(dto);
        return teacherMapper.toDTO(teacherRepository.save(entity));
    }

    public TeacherDTO update(Long id, TeacherDTO dto) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Teacher updated = teacherRepository.save(teacherMapper.toEntity(dto));
        return teacherMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Teacher entity = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        teacherRepository.delete(entity);
    }
}
