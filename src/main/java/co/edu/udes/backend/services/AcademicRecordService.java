package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.mappers.AcademicRecordMapper;
import co.edu.udes.backend.repositories.AcademicRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;
    private final AcademicRecordMapper academicRecordMapper;

    public List<AcademicRecordDTO> getAll() {
        return academicRecordRepository.findAll().stream()
                .map(academicRecordMapper::toDTO)
                .toList();
    }

    public AcademicRecordDTO getById(Long id) {
        return academicRecordMapper.toDTO(academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id)));
    }

    public AcademicRecordDTO create(AcademicRecordDTO dto) {
        return academicRecordMapper.toDTO(academicRecordRepository.save(academicRecordMapper.toEntity(dto)));
    }

    public AcademicRecordDTO update(Long id, AcademicRecordDTO dto) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        dto.setId(id);
        return academicRecordMapper.toDTO(academicRecordRepository.save(academicRecordMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        academicRecordRepository.deleteById(id);
    }
}
