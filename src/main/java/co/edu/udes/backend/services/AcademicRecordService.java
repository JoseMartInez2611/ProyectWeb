package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRecordDTO;
import co.edu.udes.backend.mappers.AcademicRecordMapper;
import co.edu.udes.backend.models.AcademicRecord;
import co.edu.udes.backend.repositories.AcademicRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;

    public List<AcademicRecordDTO> getAll() {
        List<AcademicRecord> academicRecords = academicRecordRepository.findAll();
        return AcademicRecordMapper.INSTANCE.toDtoList(academicRecords);
    }

    public AcademicRecordDTO getById(Long id) {
        return AcademicRecordMapper.INSTANCE.toDto(academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id)));
    }

    public AcademicRecordDTO create(AcademicRecord academicRecord) {
        return AcademicRecordMapper.INSTANCE.toDto(academicRecordRepository.save(academicRecord));
    }

    public AcademicRecordDTO update(Long id, AcademicRecord academicRecord) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        academicRecord.setId(id);
        return AcademicRecordMapper.INSTANCE.toDto(academicRecordRepository.save(academicRecord));
    }

    public void delete(Long id) {
        academicRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic record not found with id: " + id));
        academicRecordRepository.deleteById(id);
    }
}
