package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.mappers.AcademicRecordMapper;
import co.edu.udes.backend.models.AcademicRecord;
import co.edu.udes.backend.repositories.AcademicRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;
    @Autowired
    private AcademicRecordMapper academicRecordMapper;

    public List<AcademicRecordDTO> getAll() {
        List<AcademicRecord> academicRecords = academicRecordRepository.findAll();
        return academicRecordMapper.toDtoList(academicRecords);
    }

    public AcademicRecordDTO getById(Long id) {
        return academicRecordMapper.toDto(academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id)));
    }

    public AcademicRecordDTO create(AcademicRecord academicRecord) {
        return academicRecordMapper.toDto(academicRecordRepository.save(academicRecord));
    }

    public List<AcademicRecordDTO> createMultiple(List<AcademicRecord> academicRecords) {
        return academicRecordMapper.toDtoList(
                academicRecordRepository.saveAll(academicRecords)
        );
    }

    public AcademicRecordDTO update(Long id, AcademicRecord academicRecord) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        academicRecord.setId(id);
        return academicRecordMapper.toDto(academicRecordRepository.save(academicRecord));
    }

    public void delete(Long id) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        academicRecordRepository.deleteById(id);
    }
}
